package hkmu.comps380f.exception;

public class FavoriteBookNotFound extends Exception{
    public FavoriteBookNotFound(long id) {
        super("FavoriteBook " + id + " not found");
    }
}

