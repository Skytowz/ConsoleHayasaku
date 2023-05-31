package com.hayasaku.console.config.sentry;

import org.springframework.stereotype.Component;

import io.sentry.protocol.User;
import io.sentry.spring.jakarta.SentryUserProvider;

/**
 * Classe de configuration pour Sentry afin d'envoyer la stacktrace des erreurs sur le repo Sentry
 * @author Quentin "Ruendan" DUBOIS
 *
 */
@Component
public class PurpleSentryUserProvider implements SentryUserProvider {

    @Override
    public User provideUser() {
        User user = new User();
        user.setEmail("test@pnp.com");
        return user;
    }
}