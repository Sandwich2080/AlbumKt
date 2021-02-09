# Album
1.This is a Android album demo implemented without any third-party libraries.   
2.Using completely Kotlin code to implement.

# Features

(Plan to implement the following features)

1. Load all the images and videos files on the external storage.
2. make the files into categories by date.
3. Image decoding and memory cache.
4. Image and video preview

# Todo List
1. Video Player.
2. QRCode Scanning (Open links and show text content).
3. Third party login (including QQ, WeChat,Weibo,Facebook).
4. Push Message.
5. File management (pdf,office format files,apk,etc.).

# Latest Release
Scan the QRCode  below to install the release apk file.

（Use browsers that support QRCode scanning）

![扫码下载](https://github.com/Sandwich2080/AlbumKt/releases)

# FQA
## 1. You may face the following problem when building a release apk
   <code>
   Execution failed for task ':app:processReleaseGoogleServices'.
   > File google-services.json is missing. The Google Services Plugin cannot function without it. 
      Searched Location: 
   </code>
   The solution lies here: 
   https://stackoverflow.com/questions/33866061/error-file-google-services-json-is-missing-from-module-root-folder-the-google
