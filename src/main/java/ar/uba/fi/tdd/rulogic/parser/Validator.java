package ar.uba.fi.tdd.rulogic.parser;

public interface Validator<T> {

  boolean isValid(T t);

}
