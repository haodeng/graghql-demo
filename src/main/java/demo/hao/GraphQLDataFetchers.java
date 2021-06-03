package demo.hao;

import demo.hao.bean.Post;
import demo.hao.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static ObjectMapper oMapper = new ObjectMapper();

    private static List<Map<String, String>> posts = Arrays.asList(
            oMapper.convertValue(new Post("p1", "test1", "u1"), Map.class),
            oMapper.convertValue(new Post("p2", "test2", "u1"), Map.class),
            oMapper.convertValue(new Post("p3", "test3", "u1"), Map.class),
            oMapper.convertValue(new Post("p4", "test4", "u2"), Map.class),
            oMapper.convertValue(new Post("p5", "test5", "u2"), Map.class),
            oMapper.convertValue(new Post("p6", "test6", "u3"), Map.class)
    );

    private static List<Map<String, String>> users = Arrays.asList(
            oMapper.convertValue(new User("u1", "hao"), Map.class),
            oMapper.convertValue(new User("u2", "hao2"), Map.class),
            oMapper.convertValue(new User("u3", "hao3"), Map.class)
    );

    public DataFetcher getPostByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String postId = dataFetchingEnvironment.getArgument("id");
            return posts
                    .stream()
                    .filter(post -> post.get("id").equals(postId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getUserDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> post = dataFetchingEnvironment.getSource();
            String userId = post.get("userId");
            return users
                    .stream()
                    .filter(user -> user.get("id").equals(userId))
                    .findFirst()
                    .orElse(null);
        };
    }
}