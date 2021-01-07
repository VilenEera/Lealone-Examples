/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lealone.opscenter.service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.lealone.opscenter.service.generated.OpsService;
import org.lealone.orm.json.JsonArray;

public class OpsServiceImpl implements OpsService {
    static final String[][] LANGUAGES = { //
            { "cs", "\u010ce\u0161tina" }, //
            { "de", "Deutsch" }, //
            { "en", "English" }, //
            { "es", "Espa\u00f1ol" }, //
            { "fr", "Fran\u00e7ais" }, //
            { "hi", "Hindi \u0939\u093f\u0902\u0926\u0940" }, //
            { "hu", "Magyar" }, //
            { "ko", "\ud55c\uad6d\uc5b4" }, //
            { "in", "Indonesia" }, //
            { "it", "Italiano" }, //
            { "ja", "\u65e5\u672c\u8a9e" }, //
            { "nl", "Nederlands" }, //
            { "pl", "Polski" }, //
            { "pt_BR", "Portugu\u00eas (Brasil)" }, //
            { "pt_PT", "Portugu\u00eas (Europeu)" }, //
            { "ru", "\u0440\u0443\u0441\u0441\u043a\u0438\u0439" }, //
            { "sk", "Slovensky" }, //
            { "tr", "T\u00fcrk\u00e7e" }, //
            { "uk", "\u0423\u043A\u0440\u0430\u0457\u043D\u0441\u044C\u043A\u0430" }, //
            { "zh_CN", "\u4e2d\u6587 (\u7b80\u4f53)" }, //
            { "zh_TW", "\u4e2d\u6587 (\u7e41\u9ad4)" }, //
    };

    @Override
    public String getLanguageCombo() {
        ArrayList<List<String>> list = new ArrayList<>(LANGUAGES.length);
        for (int i = 0; i < LANGUAGES.length; i++) {
            list.add(Arrays.asList(LANGUAGES[i]));
        }
        return new JsonArray(list).encode();
    }

    @Override
    public String login(String url, String user, String password) {
        Properties prop = new Properties();
        prop.setProperty("user", user);
        prop.setProperty("password", password);
        try {
            DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            return "failed";
        }
        return "ok";
    }
}