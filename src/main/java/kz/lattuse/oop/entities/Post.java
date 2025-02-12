package kz.lattuse.oop.entities;


public class Post{
    private int id;
    private String title;
    private int authorid;
    private String content;

    public Post(int id,int authorid, String title, String content) {
        this.id = id;
        this.authorid = authorid;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(int authorid) {
        this.authorid = authorid;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post {postID=" + id + ", title='" + title + "', authorid=" + authorid + ", content='" + content + "'}";
    }
}

