package com.noxis.notecomposeapp.user

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import org.mockito.kotlin.firstValue
import java.util.UUID


class UserRepositoryImplTestMockito{

    private lateinit var dataSource: DataSource
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setUp() {
        dataSource = mock(DataSource::class.java)
        repository = UserRepositoryImpl(dataSource)
    }

    @Test
    fun `verify correct user params are used`() = runTest {
        val user = buildUser()
        repository.saveUser(user)
        val captor = ArgumentCaptor.forClass(User::class.java)
        verify(dataSource).save(captor.capture())
        assertThat(captor.firstValue.email).isEqualTo(user.email)
    }

    @Test
    fun `verify correct user is retrieved`() = runTest {
        val email = "enyasonjnr@gmail.com"
        `when`(dataSource.get(any())).then { buildUser() }

        val user = repository.getUser(email)

        assertThat(email).isEqualTo(user.email)

    }

    @Test
    fun `verify user is deleted`() = runTest {
        val email = "enyasonjnr@gmail.com"
        repository.deleteUser(email)
        verify(dataSource).clear(any())

    }


    companion object {
        fun buildUser() = User(
            id = UUID.randomUUID().toString(),
            email = "enyasonjnr@gmail.com",
            fullName = "Emmanuel Enya",
            verificationStatus = VerificationStatus.Verified,
            memberShipStatus = MemberShipStatus.Free
        )
    }

}