import parser.JsonArray;
import parser.JsonObject;

void main() {
//    String jsonString = "{\"name\": \"John\", \"age\": -30, \"address\": \"123, Main {} St\", \"nested\": {\"nestedValue\": \"value\", \"nestedBoolean\": true}, \"anotherKey\": null}";
//    String jsonString = """
//            {
//                "name"   :   "John Doe" ,
//                "age": 28,
//                "active" : true,
//                "deleted": false,
//                "nickname": null,
//                "address" : {
//                    "city" : "Tbilisi",
//                    "zip" : 0100,
//                    "verified" : true,
//                    "coordinates" : {
//                        "latitude" : 41,
//                        "longitude" : 44
//                    }
//                },
//                "score" : 0,
//                "negative" : -42,
//                "emptyString" : "",
//                "emptyObject" : {}
//            }
//            """;
//    JsonObject jsonObject = new JsonObject(jsonString);
//
//    System.out.println(jsonObject.getString("name"));
//    System.out.println(jsonObject.getInt("age"));
//    System.out.println(jsonObject.getBoolean("active"));
//    System.out.println(jsonObject.getBoolean("deleted"));
//    System.out.println(jsonObject.getString("nickname"));
//    System.out.println();
//    System.out.println(jsonObject.getJsonObject("address"));
//    System.out.println();
//    System.out.println(jsonObject.getInt("score"));
//    System.out.println(jsonObject.getInt("negative"));
//    System.out.println(jsonObject.getString("emptyString"));
//    System.out.println(jsonObject.getJsonObject("emptyObject"));

//    String nextJsonString = """
//            {
//            "company":{"name":"Acme Inc.","founded":1999,"public":false},
//            "employee":{
//                "id":1042   ,
//                "personal":{
//                    "firstName":"Alice",
//                    "lastName":"Smith",
//                    "contact":{
//                        "email":"alice@example.com",
//                        "phone":null,
//                        "emergency":{
//                            "name":"Bob",
//                            "relationship":"brother",
//                            "available":true
//                        }
//                    }
//                },
//                "employment":{
//                    "department":"Engineering",
//                    "level":3,
//                    "manager":{
//                        "name":"Carol",
//                        "active":true
//                    }
//                }
//            },
//            "metadata":{"version":1,"debug":false}
//            }""";
//
//    parser.JsonObject nextJsonObject = new parser.JsonObject(nextJsonString);
//
//    System.out.println();
//    parser.JsonObject nestedObject1 = nextJsonObject.getJsonObject("company");
//    System.out.println(nestedObject1);
//    parser.JsonObject nestedObject2 = nextJsonObject.getJsonObject("employee");
//    System.out.println(nestedObject2);
//    parser.JsonObject nestedObject3 = nestedObject2.getJsonObject("personal");
//    System.out.println(nestedObject3);
//    parser.JsonObject nestedObject4 = nestedObject2.getJsonObject("employment");
//    System.out.println(nestedObject4);
//
//    parser.JsonObject nestedObject5 = nestedObject3.getJsonObject("contact");
//    System.out.println(nestedObject5);
//
//    parser.JsonObject nestedObject6 = nestedObject5.getJsonObject("emergency");
//    System.out.println(nestedObject6);
//
//    parser.JsonObject nestedObject7 = nestedObject4.getJsonObject("manager");
//    System.out.println(nestedObject7);
//
//    parser.JsonObject metadata = nextJsonObject.getJsonObject("metadata");
//    System.out.println(metadata);

    String jsonString = """
            {
               "application": {
                 "name": "TestApp",
                 "version": "2.4.1",
                 "debug": false,
                 "metadata": null
               },
               "servers": [
                 {
                   "id": 1,
                   "host": "alpha",
                   "ports": [80, 443],
                   "enabled": true,
                   "settings": {
                     "timeout": 30,
                     "retries": 3,
                     "fallback": null
                   }
                 },
                 {
                   "id": 2,
                   "host": "beta",
                   "ports": [],
                   "enabled": false,
                   "settings": {}
                 },
                 {
                   "id": 3,
                   "host": "gamma",
                   "ports": [8080],
                   "enabled": true,
                   "settings": {
                     "timeout": 0,
                     "retries": 0,
                     "fallback": {
                       "host": "alpha",
                       "port": 443
                     }
                   }
                 }
               ],
               "features": {
                 "authentication": {
                   "enabled": true,
                   "methods": ["password", "token", "certificate"],
                   "policy": {
                     "maxAttempts": 5,
                     "lockout": {
                       "enabled": true,
                       "duration": 300
                     }
                   }
                 },
                 "caching": {
                   "enabled": false,
                   "providers": [],
                   "options": {}
                 },
                 "monitoring": {
                   "enabled": true,
                   "metrics": [
                     {
                       "name": "requests",
                       "type": "counter",
                       "labels": ["method", "status"]
                     },
                     {
                       "name": "latency",
                       "type": "histogram",
                       "labels": []
                     }
                   ]
                 }
               },
               "limits": {
                 "workers": 16,
                 "memory": 1073741824,
                 "thresholds": [0.25, 0.5, 0.75, 1.0],
                 "overrides": [
                   null,
                   0,
                   false,
                   "",
                   [],
                   {}
                 ]
               },
               "users": [],
               "permissions": {},
               "shutdown": null
             }""";

    parser.JsonObject jsonObject = new parser.JsonObject(jsonString);
    System.out.println(jsonObject);

    String jsonString1 = """
            [
              1,
              "two",
              true,
              null,
              {
                "id": 3,
                "data": [4, 5, {"x": "y"}, []]
              },
              [
                false,
                null,
                {"nested": []},
                [1, 2, 3]
              ],
              {},
              [],
              ["string", {"key": "value"}, []],
              [{}, null, "..."]
            ]""";

    JsonArray array = new JsonArray(jsonString1);

    System.out.println(array);
}
