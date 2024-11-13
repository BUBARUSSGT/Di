package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.TestUtilities.waitDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AllureValidAutch {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void shouldBeAbleToLoadList() {
        login("login2", "password2");
        checkNewsIsDisplayed();
    }

    @Step("Вход с логином: {login} и паролем: {password}")
    private void login(String login, String password) {
        Allure.step("Ждем загрузку экрана авторизации");
        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 20000));
        Allure.step("Кликаем по полю логина и вводим текст");
        onView(withId(R.id.login_text_input_layout)).perform(click());
        onView(withId(R.id.login_edit_text)).perform(click(), typeText(login));
        Allure.step("Кликаем по полю пароля и вводим текст");
        onView(withId(R.id.password_text_input_layout)).perform(click());
        onView(withId(R.id.password_edit_text)).perform(click(), typeText(password));
        Allure.step("Нажимаем кнопку входа");
        onView(withId(R.id.enter_button)).perform(click());
    }

    @Step("Проверка отображения элемента с текстом 'News'")
    private void checkNewsIsDisplayed() {
        Allure.step("Ждем появления списка новостей");
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 15000));
        Allure.step("Проверяем, что элемент 'News' отображается на экране");
        onView(withText("News")).check(matches(isDisplayed()));
    }
}
