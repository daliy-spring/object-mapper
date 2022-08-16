import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        //JSON의 default encoding은 UTF-8이다.
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("이하늘");
        user.setAge(23);


        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> list = Arrays.asList(car1, car2);
        user.setCars(list);

//        System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);


        //변수 타입이나 json 형태를 미리 알 수 있을 때 이렇게 사용 가능
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();


        System.out.println("name : " + _name);
        System.out.println("age : " + _age);

        // 아무것도 찍히지 않음
        //String _list = jsonNode.get("cars").asText();

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars;

        //이것도 이미 json이 어떻게 생겼는지 알아야 한다.
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {  });
        System.out.println(_cars);

        //json 조작하기
        ObjectNode objectNode = (ObjectNode)jsonNode;
        objectNode.put("name", "leesky");
        objectNode.put("age", 20);
        System.out.println(objectNode.toPrettyString());
    }
}