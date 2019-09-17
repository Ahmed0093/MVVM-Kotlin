package com.development.task.mvvmproductapp.list

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.development.task.mvvmproductapp.R
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class ProducListActivityTest{
    @Rule
    @JvmField
    public val rule  = getRule()
    private val username_tobe_typed="Ajesh"

    private fun getRule(): ActivityTestRule<ProducListActivity> {
        Log.e("Initalising rule","getting Mainactivity")
        return ActivityTestRule(ProducListActivity::class.java)
    }
    companion object {

        @BeforeClass
        @JvmStatic
        fun before_class_method(){
            Log.e("@Before Class","Hi this is run before anything")
        }

        @AfterClass
        @JvmStatic
        fun after_class_method(){
            Log.e("@After Class","Hi this is run after everything")
        }

    }

    @Before
    fun before_test_method(){
        Log.e("@Before","Hi this is run before every test function")
    }


    @Test
    fun login_success(){
        Log.e("@Test","Performing login success test")
        Espresso.onView((withId(R.id.tvTitleDetails)))
            .perform(ViewActions.typeText(username_tobe_typed))


    }

    @After
    fun after_test_method() {
        Log.e("@After", "Hi this is run after every test function")
    }
}