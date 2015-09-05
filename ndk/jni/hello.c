#include <string.h>
#include <jni.h>

jstring Java_com_wl_ndk_NdkActivity_getText(JNIEnv* env,jobject obj){
	return (*env)->NewStringUTF(env,"hello ndk!!");
}