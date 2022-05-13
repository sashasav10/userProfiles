package com.savelievoleksandr.userprofile.viewModel

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditProfileViewModelTest : TestCase(){
    private val viewModel = EditProfileViewModel(ApplicationProvider.getApplicationContext())

    @Test
    fun whenListContainsEmptyString() {
        val ex = listOf("ervbh", "", "rrt", "ervgt", "drfetg")
        val result = viewModel.DoesListContainNotNullValues(ex)
        Truth.assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenAllStringsInListAreNotEmpty() {
        val ex = listOf("no", "fdg", "b", "gtb", "324")
        val result = viewModel.DoesListContainNotNullValues(ex)
        Truth.assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenllStringsAreNumber() {
        val ex = listOf("5", "6", "2", "234", "324")
        val result = viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(ex)
        Truth.assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenListContainsNotValidNumber() {
        val ex = listOf("5", "6", "-2", "234", "324")
        val result = viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(ex)
        Truth.assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenListContainsLetters() {
        val ex = listOf("5", "6afvvrt", "4", "234", "324")
        val result = viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(ex)
        Truth.assertThat(result).isEqualTo(false)
    }
    @Test
    fun whenNumberListContainsEmpty() {
        val ex = listOf("no", "", "b", "gtb", "324")
        val result = viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(ex)
        Truth.assertThat(result).isEqualTo(false)
    }
}