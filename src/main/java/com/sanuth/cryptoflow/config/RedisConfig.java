package com.sanuth.cryptoflow.config;

import com.sanuth.cryptoflow.cache.PricesSubscriber;
import com.sanuth.cryptoflow.cache.RedisTopics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


        @Bean
        public LettuceConnectionFactory connectionFactory() {

            return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
        }

    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JacksonJsonRedisSerializer<Object>(Object.class));

        return template;
    }


    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                       PricesSubscriber subscriber)
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        container.addMessageListener(subscriber, pricesTopic());

        return container;
    }

    @Bean
    public ChannelTopic pricesTopic() {
        return new ChannelTopic(RedisTopics.PRICES_UPDATED);
    }


}
