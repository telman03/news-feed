package app.parser;

public enum Website {
    TechCrunch(new TechCrunchParser());

    public final JsoupParser parser;


    Website(JsoupParser parser ) {
        this.parser = parser;

    }

    public JsoupParser getParser() {
        return this.parser;
    }

}