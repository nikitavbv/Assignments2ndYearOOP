package com.nikitavbv.univ.oop.lab.validation;

public interface Validator<T> {

  Verdict validate(T input);
}