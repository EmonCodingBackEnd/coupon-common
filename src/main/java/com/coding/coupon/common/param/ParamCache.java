package com.coding.coupon.common.param;

import com.coding.coupon.common.DictDefinition;
import com.coding.coupon.common.JsonConverter;
import com.coding.coupon.common.cache.redis.AppRedisKey;
import com.coding.coupon.common.param.po.BaseParamPO;
import com.coding.helpers.tool.cmp.exception.AppBaseStatus;
import com.coding.helpers.tool.cmp.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ParamCache {

    private static RedisTemplate<String, Object> redisTemplate;

    private static ParamRepository paramRepository;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        ParamCache.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setParamRepository(ParamRepository paramRepository) {
        ParamCache.paramRepository = paramRepository;
    }

    /** 查找参数，返回的参数必须存在且没有被禁用. */
    public static <T extends BaseParamPO> T findParamNoisy(
            ParamDefinition.BaseParamDefinition<T> baseParam) {
        Optional<T> optional = findParam(baseParam);
        if (!optional.isPresent()) {
            log.error("【参数查询】根据参数代码找不到对应参数, paramCode={}", baseParam.getName());
            throw new AppException(AppBaseStatus.systemExpectedError(), "根据参数代码找不到对应参数");
        }
        T t = optional.get();
        if (!t.isEnabled()) {
            log.error("【参数查询】参数已被禁用, paramCode={}", baseParam.getName());
            throw new AppException(AppBaseStatus.systemExpectedError(), "参数已被禁用");
        }
        return t;
    }

    /** 查找参数，返回的参数可以不存在或者已被禁用. */
    public static <T extends BaseParamPO> Optional<T> findParam(
            ParamDefinition.BaseParamDefinition<T> baseParam) {
        Optional<T> optional;
        String hashKey = baseParam.getName();

        String redisKey = AppRedisKey.MANAGE_PARAM_CACHE.getKey();
        if (redisTemplate.opsForHash().hasKey(redisKey, hashKey)) {
            ParamItem paramItem = (ParamItem) redisTemplate.opsForHash().get(redisKey, hashKey);
            T t = JsonConverter.fromJson(paramItem.getParamValue(), baseParam.getClazz());
            t.setEnabled(DictDefinition.Enabled.ENABLED.equals(paramItem.getEnabled()));
            optional = Optional.of(t);
        } else {
            Param param =
                    paramRepository.findByParamCodeAndDeleted(hashKey, DictDefinition.Deleted.NO);
            if (param == null) {
                optional = Optional.empty();
            } else {
                ParamItem paramItem = new ParamItem();
                BeanUtils.copyProperties(param, paramItem);
                redisTemplate.opsForHash().put(redisKey, hashKey, paramItem);
                T t = JsonConverter.fromJson(paramItem.getParamValue(), baseParam.getClazz());
                t.setEnabled(DictDefinition.Enabled.ENABLED.equals(paramItem.getEnabled()));
                optional = Optional.of(t);
            }
        }
        return optional;
    }

    public static boolean removeParam(ParamDefinition.BaseParamDefinition baseParam) {
        boolean success;
        String hashKey = baseParam.getName();

        String redisKey = AppRedisKey.MANAGE_PARAM_CACHE.getKey();
        if (redisTemplate.opsForHash().hasKey(redisKey, hashKey)) {
            Long count = redisTemplate.opsForHash().delete(redisKey, hashKey);
            success = count > 0;
        } else {
            success = false;
        }
        return success;
    }

    public static boolean[] removeParamList(List<Param> paramList) {
        boolean[] successes = new boolean[paramList.size()];

        String redisKey = AppRedisKey.MANAGE_PARAM_CACHE.getKey();
        int i = 0;
        for (Param param : paramList) {
            String hashKey = param.getParamCode();
            if (redisTemplate.opsForHash().hasKey(redisKey, hashKey)) {
                Long count = redisTemplate.opsForHash().delete(redisKey, hashKey);
                successes[i] = count > 0;
            } else {
                successes[i] = false;
            }
            i++;
        }
        return successes;
    }
}
