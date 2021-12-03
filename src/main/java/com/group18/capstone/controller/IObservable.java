package com.group18.capstone.controller;

public interface IObservable {
        void addObserver(IObserver observer);
        void notifyAllObservers();
}
