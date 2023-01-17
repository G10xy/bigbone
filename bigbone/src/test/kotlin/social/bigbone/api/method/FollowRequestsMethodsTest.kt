package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class FollowRequestsMethodsTest {
    @Test
    fun getFollowRequests() {
        val client = MockClient.mock("follow_requests.json")

        val followRequestsMethods = FollowRequestsMethods(client)
        val pageable = followRequestsMethods.getFollowRequests().execute()
        val account = pageable.part.first()
        account.acct shouldBeEqualTo "test@test.com"
        account.displayName shouldBeEqualTo "test"
        account.userName shouldBeEqualTo "test"
    }

    @Test
    fun getFollowRequestsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val followRequestsMethods = FollowRequestsMethods(client)
            followRequestsMethods.getFollowRequests().execute()
        }
    }
}