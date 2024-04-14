package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favoriteBooks")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Favoriteid;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    public Long getFavoriteid() {
        return Favoriteid;
    }

    public void setFavoriteid(Long favoriteid) {
        Favoriteid = favoriteid;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
