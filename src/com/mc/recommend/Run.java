package com.mc.recommend;

import com.mc.recommend.presentation.Menu;

public class Run {
    public static void main(String[] args) {
        new Menu().main();
    }
}





/*Gson gson = new Gson();
        System.out.println(gson.toJson(new SongMessage())); // {}  --  빈 json이 만들어짐

        Gson gson = new GsonBuilder().serializeNulls().create(); // null 값을 디폴트로 넣어줌
        System.out.println(gson.toJson(new SongMessage())); // {"singer":null,"title":null,"reason":null}
        */

        /*ResponseFormat<SongMessage> formatter = new ResponseFormat<>(SongMessage.class);
        String json = formatter.formatToJson();
        System.out.println(json);*/

        /*String msg = "우울함을 없애주는 노래 한 곡 추천해줘.";
        GeminiChatModel model = GeminiChatModel.getInstance();
        BaseRequest request = new TextRequest(msg, new ResponseFormat<SongMessage>(SongMessage.class));
        BaseResponse response = model.invoke(request); // 추상화된 메소드
        SongMessage song = response.response(SongMessage.class);
        System.out.println(song);*/

/*
    public static void main(String[] args) throws IOException, InterruptedException {

        // part 1
        String uri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyBLI0bfwny7l-Hx56hI93CxYpHQZXxxnyQ";

*/
/*        String body = "{\n" +
                "  \"contents\": [\n" +
                "    {\n" +
                "      \"parts\": [\n" +
                "        {\n" +
                "          \"text\": \"신나는 노래 한 곡 추천해줘! 30자 이내로 대답해줘\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";*//*


        // 방법 3  :Body 부터 다시 만듦
        TextPart part = new TextPart("신나는 노래 한 곡 추천해줘");
        TextContent content = new TextContent(List.of(part));
        TextRequestDocument doc = new TextRequestDocument(List.of(content));
        // 객체를 문자열로
        Gson gson = new Gson();
        String body = gson.toJson(doc);
        System.out.println(body);
        //

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.statusCode());
        //System.out.println(response.body());
        String jsonText = response.body();

        // 방법 3 - 이어서
        TextResponseDocument res  = gson.fromJson(jsonText, TextResponseDocument.class);
        System.out.println(res); // gemini api 응답 형태에 맞음
        //

        // part 2
        //Gson gson = new Gson(); // Or use new GsonBuilder().create();

        */
/* 방법 1
        // 요즘에는 Object Map으로 받아서 쓰는 거 안 함...
        Map<String, Object> map = gson.fromJson(jsonText, HashMap.class);
        System.out.println(map);

        {candidates=[{content={parts=[{text=오늘 점심은 칼칼한 김치찌개 또는 시원한 잔치국수 어때요?
        }], role=model}, finishReason=STOP, avgLogprobs=-0.08808558958548086}], modelVersion=gemini-1.5-flash, usageMetadata={promptTokenCount=22.0, candidatesTokenCount=27.0, totalTokenCount=49.0}}
         *//*


 */
/* 방법 2
        JsonObject jsonObject = gson.fromJson(jsonText, JsonObject.class); // 두번째 인자로 어떤 타입으로 받을 건지 넣어줌
        jsonObject = jsonObject.getAsJsonArray("candidates")
                .get(0).getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject("content")
                .getAsJsonArray("parts")
                .get(0).getAsJsonObject();

        System.out.println(jsonObject.get("text").getAsString());
        *//*



    }
}
*/