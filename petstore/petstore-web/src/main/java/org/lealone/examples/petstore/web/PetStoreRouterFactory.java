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
package org.lealone.examples.petstore.web;

import java.util.Map;

import org.lealone.server.http.HttpRouterFactory;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class PetStoreRouterFactory extends HttpRouterFactory {
    @Override
    protected void initRouter(Map<String, String> config, Vertx vertx, Router router) {
        System.setProperty("vertxweb.environment", "development");
        String webRoot = config.get("web_root");
        String templateRoot = webRoot;
        // templateRoot = webRoot + "/templates/";
        PetStoreThymeleafTemplateEngine templateEngine = new PetStoreThymeleafTemplateEngine(vertx, templateRoot);
        testThymeleaf(templateEngine, router);

        router.route("/").handler(routingContext -> {
            routingContext.redirect("/home/index.html"); // 需要对index.html进行后端渲染
        });
        router.route("/fragment/*").handler(routingContext -> {
            routingContext.fail(404);// 不允许访问Thymeleaf的fragment文件
        });

        // 用正则表达式判断路径是否以“.html”结尾（不区分大小写）
        router.routeWithRegex(".*\\.(?i)html").handler(routingContext -> {
            JsonObject jsonObject = new JsonObject();
            String file = routingContext.request().path();
            templateEngine.render(jsonObject, file).onSuccess(buffer -> {
                response(routingContext, buffer);
            }).onFailure(cause -> {
                routingContext.fail(cause);
            });
        });
    }

    private static void testThymeleaf(PetStoreThymeleafTemplateEngine templateEngine, Router router) {
        router.route("/thymeleaf_hello").handler(routingContext -> {
            JsonObject jsonObject = new JsonObject().put("msg", "Hello Thymeleaf!");
            templateEngine.render(jsonObject, "/thymeleaf/hello.html").onSuccess(buffer -> {
                response(routingContext, buffer);
            }).onFailure(cause -> {
                routingContext.fail(cause);
            });
        });
        router.route("/thymeleaf_fragment").handler(routingContext -> {
            final JsonObject context = new JsonObject().put("foo", "badger").put("bar", "fox").put("context",
                    new JsonObject().put("path", "/thymeleaf/test-thymeleaf-template2.html"));
            templateEngine.render(context, "/thymeleaf/test-thymeleaf-fragmented.html").onSuccess(buffer -> {
                response(routingContext, buffer);
            }).onFailure(cause -> {
                routingContext.fail(cause);
            });
        });
    }

    private static void response(RoutingContext routingContext, Buffer buffer) {
        routingContext.response().putHeader("Content-Type", "text/html; charset=utf-8").end(buffer);
    }
}
