package com.profilepractice;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfilPresenterTest {

    @Mock
    private ProfilView view;
    @Mock
    private ProfilService service;
    private ProfilPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ProfilPresenter(view, service);
    }

    @Test
    public void showErrorMessageWhenNumeIsEmpty() throws Exception{

        when(view.getNume()).thenReturn("");
        presenter.verifyNameEmpty();
        verify(view).showNumeError(R.string.nume_error);
    }

    @Test
    public void showErrorMessageWhenNumeIsInvalid() throws Exception{

        when(view.getNume()).thenReturn("+-@;:");
        presenter.verifyNameInvalid();
        verify(view).showNumeError(R.string.nume_error);
    }

    @Test
    public void showErrorMessageWhenGreutateIsNegative() throws Exception{
        when(view.getGreutate()).thenReturn("-50");
        presenter.verifyWeight();
        verify(view).showGreutateError(R.string.greutate_error);
    }

}