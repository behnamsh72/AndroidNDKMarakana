# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)




# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        fibo-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        fibNative.cpp)

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

add_library( app-glue
        STATIC
        ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c )
target_link_libraries( # Specifies the target library.
        fibo-lib
        app-glue

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})


#[[add_library(friendlyarm-thing SHARED IMPORTED)

SET_TARGET_PROPERTIES(#Specifies the target library.
        friendlyarm-things
        PROPERTIES IMPORTED_LOCATION
        libfriendlyarm-things.so)]]


