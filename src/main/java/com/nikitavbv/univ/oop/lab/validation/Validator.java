package com.nikitavbv.univ.oop.lab.validation;

public interface Validator<T> {

  void validate(T input);
}