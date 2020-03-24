package site.wetsion.app.qycloudbookmark.common.constant;

/**
 * Created by wetsion on 2020/3/23.
 */
public interface AuthConstant {

    String LICENSE = "Made by wetsion, Copyright © wetsion";

    String FROM = "from";

    String FROM_IN = "inner";

    String AUTH_PREFIX = "qy:oauth:";

    String AUTH_CLIENT_DETAILS = AUTH_PREFIX + "client:details";

    String CLIENT_FIELDS = "id as client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
            + " from oauth_client_details";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by id";

    /**
     * 按条件id(当作client_id)查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where id = ?";
}
