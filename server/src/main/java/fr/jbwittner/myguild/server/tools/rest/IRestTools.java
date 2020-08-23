package fr.jbwittner.myguild.server.tools.rest;

public interface IRestTools {

    public <T> T get(String url, Class<T> type);
}