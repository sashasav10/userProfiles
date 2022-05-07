package com.savelievoleksandr.userprofile.viewModel

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditProfileViewModelTest : TestCase(){
    private val viewModel = AddUserProfileViewModel(ApplicationProvider.getApplicationContext())

    @Test
    fun whenListIsEmpty() {
        val ex = listOf("")
        val result = viewModel.isEmpty(ex)
        Truth.assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenListContainsEmpty() {
        val ex = listOf("ervbh", "", "rrt", "ervgt", "drfetg")
        val result = viewModel.isEmpty(ex)
        Truth.assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenStringIsNotEmpty() {
        val ex = listOf("no", "fdg", "b", "gtb", "324")
        val result = viewModel.isEmpty(ex)
        Truth.assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenStringIsNumber() {
        val ex = listOf("5", "6", "2", "234", "324")
        val result = viewModel.isNumberValid(ex)
        Truth.assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenStringIsNotValidNumber() {
        val ex = listOf("5", "6", "-2", "234", "324")
        val result = viewModel.isNumberValid(ex)
        Truth.assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenNumberContainsLetters() {
        val ex = listOf("5", "6afvvrt", "4", "234", "324")
        val result = viewModel.isNumberValid(ex)
        Truth.assertThat(result).isEqualTo(false)
    }
    @Test
    fun whenNumbersContainsEmpty() {
        val ex = listOf("no", "", "b", "gtb", "324")
        val result = viewModel.isNumberValid(ex)
        Truth.assertThat(result).isEqualTo(false)
    }
    @Test
    fun whenNumberListIsEmpty() {
        val ex = listOf("")
        val result = viewModel.isNumberValid(ex)
        Truth.assertThat(result).isEqualTo(false)
    }
}