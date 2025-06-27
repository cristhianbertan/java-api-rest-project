package com.AgriPecu.AgriPecu.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching // Garante que o caching está habilitado
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("consultasClima"); // Define o nome do cache
        cacheManager.setCaffeine(caffeineConfig());
        return cacheManager;
    }

    Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS) // Tempo de expiração: 60 segundos
                .maximumSize(500) // Tamanho máximo do cache: 500 entradas
                .recordStats(); // Opcional: para coletar estatísticas de cache
    }
}