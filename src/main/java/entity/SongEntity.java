package entity;

import javax.persistence.*;

/**
 * Created by liuhu on 2018/1/12.
 */
@Entity
@Table(name = "song", schema = "music", catalog = "")
public class SongEntity {
    private int id;
    private String infoUrl;
    private String name;
    private String singer;
    private String singPageUrl;
    private String singSourceUrl;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "infoUrl")
    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "singer")
    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Basic
    @Column(name = "singPageUrl")
    public String getSingPageUrl() {
        return singPageUrl;
    }

    public void setSingPageUrl(String singPageUrl) {
        this.singPageUrl = singPageUrl;
    }

    @Basic
    @Column(name = "singSourceUrl")
    public String getSingSourceUrl() {
        return singSourceUrl;
    }

    public void setSingSourceUrl(String singSourceUrl) {
        this.singSourceUrl = singSourceUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongEntity that = (SongEntity) o;

        if (id != that.id) return false;
        if (infoUrl != null ? !infoUrl.equals(that.infoUrl) : that.infoUrl != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (singer != null ? !singer.equals(that.singer) : that.singer != null) return false;
        if (singPageUrl != null ? !singPageUrl.equals(that.singPageUrl) : that.singPageUrl != null) return false;
        if (singSourceUrl != null ? !singSourceUrl.equals(that.singSourceUrl) : that.singSourceUrl != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (infoUrl != null ? infoUrl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (singer != null ? singer.hashCode() : 0);
        result = 31 * result + (singPageUrl != null ? singPageUrl.hashCode() : 0);
        result = 31 * result + (singSourceUrl != null ? singSourceUrl.hashCode() : 0);
        return result;
    }
}
