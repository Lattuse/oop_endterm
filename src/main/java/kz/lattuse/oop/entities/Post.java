package kz.lattuse.oop.entities;


public class Post extends Entity {
    private static int postGenId = 0;
    private String title;
    private final int authorId;
    private String content;

    public Post(int authorId, String title, String content) {
        super(postGenId++);
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post {postID=" + id + ", title='" + title + "', authorId=" + authorId + ", content='" + content + "'}";
    }
}

