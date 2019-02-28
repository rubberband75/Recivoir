package com.rubberband75.recivoir;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {

    private static final String FAKE_STRING = "HELLO WORLD";

    @Mock
    Context mockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
//        // Given a mocked Context injected into the object under test...
//        when(mockContext.getString(R.string.hello_world))
//                .thenReturn(FAKE_STRING);
//        ClassUnderTest myObjectUnderTest = new ClassUnderTest(mockContext);
//
//        // ...when the string is returned from the object under test...
//        String result = myObjectUnderTest.getHelloWorldString();
//
//        // ...then the result should be the expected one.
//        assertThat(result, is(FAKE_STRING));
    }


    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {

        assertThat("abc").isEqualTo(123);
    }

    @Test
    public void verifyRecipeLoader() {
//        int recipeID = 123;

//        Recipe r = Model.getRecipeFromDB(recipeID);

//        assertThat(r.title = "Test Recipe");

        assertThat("recipe").isEqualTo("recipeFromDB");
    }


    @Test
    public void verifyLogin() {
        String username = "rubberband75";
        String password = "baconPancakes";

//        User u = login(username, password);

//        assert

        }

    }
}