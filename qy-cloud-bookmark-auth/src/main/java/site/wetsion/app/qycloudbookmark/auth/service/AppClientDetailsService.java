package site.wetsion.app.qycloudbookmark.auth.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import site.wetsion.app.qycloudbookmark.common.constant.AuthConstant;

import javax.sql.DataSource;

/**
 * 客户端
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/24 11:37 AM
 */
public class AppClientDetailsService extends JdbcClientDetailsService {

    public AppClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    @Cacheable(value = AuthConstant.AUTH_CLIENT_DETAILS, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = super.loadClientByClientId(clientId);
        return clientDetails;
    }
}
