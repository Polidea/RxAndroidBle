package com.polidea.rxandroidble3.internal.util

import android.os.Build
import com.polidea.rxandroidble3.BuildConfig
import hkhc.electricspock.ElectricSpecification
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE, constants = BuildConfig, sdk = Build.VERSION_CODES.LOLLIPOP)
class UUIDUtilTest extends ElectricSpecification {

    static final UNKNOWN_DATA_TYPE = 0xfa
    UUIDUtil objectUnderTest = new UUIDUtil()

    def "should extract 32-bit UUIDs"() {

        given:
        def advertisement = [ 0x05, 0x04, 0xef, 0xbe, 0xad, 0xde ] as byte[]

        when:
        def result = objectUnderTest.extractUUIDs(advertisement)

        then:
        result.get(0).toString() == "deadbeef-0000-1000-8000-00805f9b34fb"
    }

    def "should not throw if fed with unknown data type that suggest longer length than actual"() {

        given:
        def advertisement = [ 0x11 /* length */, UNKNOWN_DATA_TYPE, 0xef, 0xbe, 0xad,  0xde ] as byte[]

        when:
        objectUnderTest.extractUUIDs(advertisement)

        then:
        noExceptionThrown()
    }

    def "should not throw if unknown data type length is >128"() {

        given:
        def advertisement = [ 0x91 /* length 145 */, UNKNOWN_DATA_TYPE, 0xef, 0xbe, 0xad,  0xde,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01 ] as byte[]

        when:
        objectUnderTest.extractUUIDs(advertisement)

        then:
        noExceptionThrown()
    }

    def "should extract 32-bit UUIDs after a long unknown data type"() {

        given:
        def advertisement = [ 0x91 /* length 145 */, UNKNOWN_DATA_TYPE, 0xef, 0xbe, 0xad,  0xde,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
                              0x05, 0x04, 0xef, 0xbe, 0xad, 0xde ] as byte[]

        when:
        def result = objectUnderTest.extractUUIDs(advertisement)

        then:
        result.get(0).toString() == "deadbeef-0000-1000-8000-00805f9b34fb"
    }
}
