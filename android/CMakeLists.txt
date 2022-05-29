cmake_minimum_required(VERSION 3.9.0)

set (PACKAGE_NAME "nativeJsiDlog")
set (BUILD_DIR ./build)
set (CMAKE_VERBOSE_MAKEFILE ON)
set (CMAKE_CXX_STANDARD 11)

# Specifies a path to native header files.
include_directories(
        "${NODE_MODULES_DIR}/react-native/React"
        "${NODE_MODULES_DIR}/react-native/React/Base"
        "${NODE_MODULES_DIR}/react-native/ReactCommon/jsi"
        ../cpp
)

file (GLOB LIBRN_DIR "${BUILD_DIR}/react-native-0*/jni/${ANDROID_ABI}")
message(STATUS "LIBRN_DIR: ${LIBRN_DIR}")

if (EXISTS "${LIBRN_DIR}/libjsi.so")
  # React Native 0.66.x and above
  find_library(
          JSI_LIB
          jsi
          PATHS ${LIBRN_DIR}
          NO_CMAKE_FIND_ROOT_PATH
  )
else()
  # React Native 0.65.x and below
  set (JSI_LIB "")
endif()

add_library(
        ${PACKAGE_NAME}
        SHARED
        ../cpp/rn-jsi-dlog.cpp
        ./cpp-adapter.cpp
)

find_library(
        REACT_NATIVE_JNI_LIB
        reactnativejni
        PATHS ${LIBRN_DIR}
        NO_CMAKE_FIND_ROOT_PATH
)

set_target_properties(
        ${PACKAGE_NAME} PROPERTIES
        CXX_STANDARD 17
        CXX_EXTENSIONS OFF
        POSITION_INDEPENDENT_CODE ON
)

target_link_libraries(
        ${PACKAGE_NAME}
        ${REACT_NATIVE_JNI_LIB}
        ${JSI_LIB}
        android
)
