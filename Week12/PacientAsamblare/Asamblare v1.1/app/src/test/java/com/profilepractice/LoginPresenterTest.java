package com.profilepractice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import utility.LoginAsync;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    @Mock
    private LoginView view;
    @Mock
    private LoginService service;
    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {
        when(view.getUsername()).thenReturn("");
        presenter.userLogin();

        verify(view).showUsernameError(R.string.username_error);
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        when(view.getUsername()).thenReturn("user");
        when(view.getPassword()).thenReturn("");
        presenter.userLogin();

        verify(view).showPasswordError(R.string.password_error);
    }

    @Test
    public void shouldStartProfileActivityWhenUsernameAndPasswordAreCorrect() throws Exception {
        when(view.getUsername()).thenReturn("user");
        when(view.getPassword()).thenReturn("test");
        when(service.login("user", "test")).thenReturn(true);
        presenter.userLogin();

        verify(view).startActivity();
    }

    @Test
    public void shouldShowLoginErrorWhenUsernameAndPasswordAreInvalid() throws Exception {
        when(view.getUsername()).thenReturn("user");
        when(view.getPassword()).thenReturn("test");
        when(service.login("user", "test")).thenReturn(false);
        presenter.userLogin();

        verify(view).showLoginError(R.string.login_failed);
    }
}