package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.TestUtilities.waitDisplayed;

import ru.iteco.fmhandroid.R;

public class AuthorizationPage {
    private final int loginFieldId = R.id.login_text_input_layout;

    public void waitAuthorizationPage() {
        onView(isRoot()).perform(TestUtilities.waitDisplayed(loginFieldId, 7000));
    }
}

