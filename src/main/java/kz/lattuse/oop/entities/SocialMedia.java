package kz.lattuse.oop.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SocialMedia {
    private String platformName;
    private final List<User> users;
    private final List<Post> posts;

    public SocialMedia(String platformName) {
        this.platformName = platformName;
        this.users = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return posts.stream()
                .filter(post -> post.getTitle().contains(keyword) || post.getContent().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<User> searchUsersByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void sortPostsByTitle() {
        posts.sort((p1, p2) -> p1.getTitle().compareToIgnoreCase(p2.getTitle()));
    }

    @Override
    public String toString() {
        return "SocialMedia {platformName='" + platformName + "', users=" + users + ", posts=" + posts + "}";
    }
}

