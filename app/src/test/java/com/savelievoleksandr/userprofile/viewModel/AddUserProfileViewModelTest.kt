package com.savelievoleksandr.userprofile.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.savelievoleksandr.userprofile.model.User
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class AddUserProfileViewModelTest : TestCase() {
    private lateinit var repository: UserRepository
    private lateinit var viewModel : AddUserProfileViewModel

    @Before
    fun setup() {
        repository = mock(UserRepository::class.java)
        viewModel = AddUserProfileViewModel(repository)
    }

    @Test
    @Throws(Exception::class)
    fun addUser() {
       val id=viewModel.size+1
        val user = User(id,"Dafna","https://w7.pngwing.com/pngs/823/242/png-transparent-daphne-scooby-doo-velma-dinkley-shaggy-rogers-character-daphne.png",
        "online","12","123","23","I'm Dafna","076543456","dafna@secret.inc")
        viewModel.insertUser(user)
        val userFoundById: User =viewModel.getUserById(id)
        assertEquals(user, userFoundById)
    }
    @Test
    fun whenListContainsEmptyString() {
        val ex = listOf("ervbh", "", "rrt", "ervgt", "drfetg")
        val result = viewModel.DoesListContainNotNullValues(ex)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenAllStringsInListAreNotEmpty() {
        val ex = listOf("no", "fdg", "b", "gtb", "324")
        val result = viewModel.DoesListContainNotNullValues(ex)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenStringIsNumber() {
        val ex = listOf("5", "6", "2", "234", "324")
        val result = viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(ex)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenStringIsNotValidNumber() {
        val ex = listOf("5", "6", "-2", "234", "324")
        val result = viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(ex)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenNumberContainsLetters() {
        val ex = listOf("5", "6afvvrt", "4", "234", "324")
        val result = viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(ex)
        assertThat(result).isEqualTo(false)
    }
    @Test
    fun whenNumbersContainsEmpty() {
        val ex = listOf("no", "", "b", "gtb", "324")
        val result = viewModel.doesListContainOnlyNotEmptyNumbersBiggerThanZero(ex)
        assertThat(result).isEqualTo(false)
    }
  
}