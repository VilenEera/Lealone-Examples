package org.lealone.opscenter.service.generated.executor;

import java.util.Map;
import org.lealone.db.service.ServiceExecutor;
import org.lealone.db.value.*;
import org.lealone.opscenter.service.OpsServiceImpl;
import org.lealone.orm.json.JsonArray;

/**
 * Service executor for 'ops_service'.
 *
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
public class OpsServiceExecutor implements ServiceExecutor {

    private final OpsServiceImpl s = new OpsServiceImpl();

    @Override
    public Value executeService(String methodName, Value[] methodArgs) {
        switch (methodName) {
        case "GET_LANGUAGES":
            String result1 = this.s.getLanguages();
            if (result1 == null)
                return ValueNull.INSTANCE;
            return ValueString.get(result1);
        case "GET_SETTINGS":
            String p_setting_2 = methodArgs[0].getString();
            String result2 = this.s.getSettings(p_setting_2);
            if (result2 == null)
                return ValueNull.INSTANCE;
            return ValueString.get(result2);
        case "READ_TRANSLATIONS":
            String p_language_3 = methodArgs[0].getString();
            String result3 = this.s.readTranslations(p_language_3);
            if (result3 == null)
                return ValueNull.INSTANCE;
            return ValueString.get(result3);
        case "LOGIN":
            String p_url_4 = methodArgs[0].getString();
            String p_user_4 = methodArgs[1].getString();
            String p_password_4 = methodArgs[2].getString();
            String result4 = this.s.login(p_url_4, p_user_4, p_password_4);
            if (result4 == null)
                return ValueNull.INSTANCE;
            return ValueString.get(result4);
        default:
            throw new RuntimeException("no method: " + methodName);
        }
    }

    @Override
    public String executeService(String methodName, Map<String, Object> methodArgs) {
        switch (methodName) {
        case "GET_LANGUAGES":
            String result1 = this.s.getLanguages();
            if (result1 == null)
                return null;
            return result1;
        case "GET_SETTINGS":
            String p_setting_2 = ServiceExecutor.toString("SETTING", methodArgs);
            String result2 = this.s.getSettings(p_setting_2);
            if (result2 == null)
                return null;
            return result2;
        case "READ_TRANSLATIONS":
            String p_language_3 = ServiceExecutor.toString("LANGUAGE", methodArgs);
            String result3 = this.s.readTranslations(p_language_3);
            if (result3 == null)
                return null;
            return result3;
        case "LOGIN":
            String p_url_4 = ServiceExecutor.toString("URL", methodArgs);
            String p_user_4 = ServiceExecutor.toString("USER", methodArgs);
            String p_password_4 = ServiceExecutor.toString("PASSWORD", methodArgs);
            String result4 = this.s.login(p_url_4, p_user_4, p_password_4);
            if (result4 == null)
                return null;
            return result4;
        default:
            throw new RuntimeException("no method: " + methodName);
        }
    }

    @Override
    public String executeService(String methodName, String json) {
        JsonArray ja = null;
        switch (methodName) {
        case "GET_LANGUAGES":
            String result1 = this.s.getLanguages();
            if (result1 == null)
                return null;
            return result1;
        case "GET_SETTINGS":
            ja = new JsonArray(json);
            String p_setting_2 = ja.getString(0);
            String result2 = this.s.getSettings(p_setting_2);
            if (result2 == null)
                return null;
            return result2;
        case "READ_TRANSLATIONS":
            ja = new JsonArray(json);
            String p_language_3 = ja.getString(0);
            String result3 = this.s.readTranslations(p_language_3);
            if (result3 == null)
                return null;
            return result3;
        case "LOGIN":
            ja = new JsonArray(json);
            String p_url_4 = ja.getString(0);
            String p_user_4 = ja.getString(1);
            String p_password_4 = ja.getString(2);
            String result4 = this.s.login(p_url_4, p_user_4, p_password_4);
            if (result4 == null)
                return null;
            return result4;
        default:
            throw new RuntimeException("no method: " + methodName);
        }
    }
}
