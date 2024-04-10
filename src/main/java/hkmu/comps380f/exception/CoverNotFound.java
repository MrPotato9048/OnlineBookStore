package hkmu.comps380f.exception;

import java.util.UUID;

public class CoverNotFound extends Exception{
    public CoverNotFound(UUID id) {
        super("Cover " + id + " does not exist.");
    }
}
