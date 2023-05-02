package com.bigbrotherlee.jaria2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.io.IOException;
import java.io.InputStream;

public class OptionsTomlParseTest {

    private InputStream inputStream;

    @BeforeEach
    public void resource(){
        inputStream = this.getClass().getClassLoader().getResourceAsStream("options.toml");
    }

    @Test
    public void parse() throws IOException {
        TomlParseResult parse = Toml.parse(inputStream);
        String s = parse.toJson();
        System.out.println(s);
    }
}
