/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.vkontakte.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.vkontakte.api.VKontakte;

/**
 * VKontakte ConnectionFactory implementation.
 * @author vkolodrevskiy
 */
public class  VKontakteConnectionFactory extends OAuth2ConnectionFactory<VKontakte> {

	public VKontakteConnectionFactory(String clientId, String clientSecret) {
		super("vkontakte", new VKontakteServiceProvider(clientId, clientSecret), new VKontakteAdapter());
	}

    @Override
    public Connection<VKontakte> createConnection(ConnectionData data) {
        VKontakteServiceProvider serviceProvider = (VKontakteServiceProvider) getServiceProvider();
        VKontakteOAuth2Template template = (VKontakteOAuth2Template) serviceProvider.getOAuthOperations();
        if (template.getUid() == null) {
            template.setUid(data.getProviderUserId());
        }
        return new OAuth2Connection<VKontakte>(data, serviceProvider, getApiAdapter());
    }
}
