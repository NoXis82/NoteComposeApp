package com.noxis.notecomposeapp.user

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.UUID

class UserRepositoryImplTest{

    private lateinit var dataSource: DataSource
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setUp() {
        dataSource = mockk<DataSource>(relaxed = true)
        repository = UserRepositoryImpl(dataSource)
    }

    @Test
    fun `verify correct user params are used`() = runTest {
        val user = buildUser()
        repository.saveUser(user)
        val captor = slot<User>()

        coVerify { dataSource.save(capture(captor)) }

        assertThat(captor.captured.email).isEqualTo(user.email)
    }

    @Test
    fun `verify correct user is retrieved`() = runTest {
        val email = "enyasonjnr@gmail.com"

        coEvery { dataSource.get(any()) } returns buildUser()

        val user = repository.getUser(email)
        assertThat(email).isEqualTo(user.email)

    }

    @Test
    fun `verify user is deleted`() = runTest {
        val email = "enyasonjnr@gmail.com"
        repository.deleteUser(email)

        coVerify { dataSource.clear(any()) }

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