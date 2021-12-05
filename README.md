IoTBell Service
=================
## 0. Github Clone (used Github Desktop / **입문자 강력 추천**)

### Github Desktop이란?  
>WINDOWS에서도 GIT 소스를 GUI로 편리하게 관리할 수 있는 TOOL 

### Github Desktop 으로 repository clone 하기
> 1.GitHub Desktop 을 실행합니다.  
>##### (설치 안하신분들은 여기 [링크](https://desktop.github.com/) 참고하셔서 Github Desktop 을 설치하세요.)  
> 2.Clone 하려는 github 의 repository url 을 복사합니다.  
>
> ![repository url](https://user-images.githubusercontent.com/94885063/144436672-ed4aedeb-a6b9-4f7e-8355-8685b3dd8538.png)
>##### 복사하니 <https://github.com/S-RSH/IoTCloudPlatfoam.git>가 나옵니다.  
> 3.GitHub Desktop 에서 Add -> Clone repository 를 클릭합니다. 
>
> ![Clone repository](https://user-images.githubusercontent.com/94885063/144436610-3b9203cd-22bf-44e2-913b-71bdebf48191.png)
>
>##### 그리고 URL 탭에서 URL 을 붙혀넣기 하면, Local path 에 해당 repository name 으로 폴더가 생깁니다.
>
> ![Local path](https://user-images.githubusercontent.com/94885063/144438078-3458bc05-6e22-4f5c-8769-9fa89c32c7f3.png)
>
>##### **[Clone]** 버튼을 누릅니다.


### Github Desktop 으로 Publish repository 하여 Github에 파일 업로드하기
>##### Local path 경로를 확인해보면 IoTCloudPlatfoam라는 이름에 빈 폴더가 생성되신 것을 확인해보실 수 있습니다.
>
> ![Folder](https://user-images.githubusercontent.com/94885063/144441527-55c1f1bc-ae16-49a6-9d30-10f55b9581f8.png)
>
>##### IoTCloudPlatfoam 폴더에 _※_aws_setting 라는 이름에 txt 파일을 만들었습니다.
>
> ![File upload](https://user-images.githubusercontent.com/94885063/144442366-157e5d95-0ead-4018-91c9-b185d1710c08.png)
>
>##### github desktop을 확인해보시면 _※_aws_setting.txt 파일이 새로 올라온 것을 확인해보실 수 있습니다.  
>##### 노란색으로 밑줄 친 곳에 업로드 할 파일에 설명을 간단하게 적어주세요.
>##### Commit to master를 클릭하신 후에 Publish repository를 클릭해주세요.
>
> ![publish respoitory](https://user-images.githubusercontent.com/94885063/144444820-832fe087-0e27-4baf-9a71-b39e42273642.png)
>
>##### Publish repository를 클릭하시면 아래와 같은 창이 뜨는데 Keep this code private에 체크 박스를 풀어주시고
>##### Publish repository 버튼을 클릭해주세요.
>
>![final](https://user-images.githubusercontent.com/94885063/144447879-4293cdd3-0e5b-4b66-aa5f-e8892cc91caf.png)
>
>##### github.com 을 확인해보시면 IoTCloudPlatfoam 프로젝트가 업로드된 것을 확인해보실 수 있습니다.
***


## 1. 

### 구성 요소
>
>![구성요소](https://user-images.githubusercontent.com/94885063/144728589-8f7a2506-ad60-4827-abee-206672233c8f.PNG)
>
### Raspberry Pi 4B 서비스 환경
> 
> Raspberry Pi 4B에 3가지를 다운로드 했다. AWS CLI, Boto3, Open CV.
> 
> AWS CLI - AWS를 커맨드로 사용하기 위해 다운한다.
> 
> Boto3 - Python용 AWS SDK이다. Boto3를 사용하면 Python Script를 AWS S3, DynamoDB, SNS 등 AWS 서비스와 쉽게 연동할 수 있다.
> 
> Open CV - Open CV 오픈 소스 중에서 videocapture 클래스를 이용하여 카메라 입력과 동영상 파일 처리를 하기 위해 사용하였다. 
>
### AWS CLI 설치 방법
>
> Raspberry Pi termianl을 열고 다음과 같은 코드를 작성한다.
>
~~~
pip3 install awscli
~~~
>
> ![pip](https://user-images.githubusercontent.com/94885063/144750211-ac4565c3-f044-4c02-bc13-4fc9da8cd57a.PNG)
>##### 위와 같은 방식으로 진행되며 설치가 완료 된다.

### AWS CLI를 통하여 Raspberry Pi 4B 사용자 설정 방법
>
> IAM Console 인증을 사용하였다.
> 
> 1.IAM 사용자 생성
> ##### 우선 새롭게 생성할 사용자를 만든다.
>
> ![IAM](https://user-images.githubusercontent.com/94885063/144750584-55f559f2-25ad-4e80-a67f-d33e3622027f.PNG)
>
> ##### 사용자 추가를 누른다.
>
> ![사용자추가](https://user-images.githubusercontent.com/94885063/144750633-1f444903-9f42-404f-a077-8bc03b4a0b99.PNG)
>
> ##### raspberrypiuser를 사용자 이름에 입력하고 자격 증명 유형 선택에서 액세스 키를 선택한 후 다음으로 넘어간다.
>
> ##### 정책 선택이 나온다. 
>
> ![policy](https://user-images.githubusercontent.com/94885063/144750684-9fb72db3-a877-4352-a564-c0e940506e17.PNG)
>
> ##### 여러 옵션이 나온다. 사용해야할 정책들을 이미 정했다면 해당 정책을 사용한다. 잘모르겠다면 AdministratorAccess을 선택한다. 최고 권한을 가진 정책이기 때문에 하고 싶은 기능을 모두 이용할 수 있다. 정책을 선택했다면 다음으로 넘어간다.
> 
> ![태그](https://user-images.githubusercontent.com/94885063/144750778-65ba8037-8eb4-4c72-ab1c-e1d49d5c3af5.PNG)
>
> ###### 우리는 추가해야할 태그가 없으므로 다음으로 넘어간다. 
>
>![검토](https://user-images.githubusercontent.com/94885063/144750842-6fbb36f8-a34d-4dd2-a0f8-d99290358bc5.PNG)
>
> ##### 방금 우리가 설정한 부분들을 보여주며 AWS가 마지막 확인을 도와준다. 확인을 한 후 다음으로 넘어간다.
>
> ![사용자추가1](https://user-images.githubusercontent.com/94885063/144750897-4c062a97-75fb-4689-8079-a69f49c3b4f2.png)
>
> ##### 액세스 키 ID, 비밀 액세스 키, 그리고 .CSV 다운로드가 나온다. 이후 인증 과정 가운데 필요하므로 전부 저장을 한다.  
>
> 2.Raspberry Pi 4B 사용자 설정 하기
>
~~~
aws configure
~~~
> ##### 위 코드를 입력하면 Access Key ID를 입력하라고 한다. '1.IAM 사용자 생성'에서 생성한 액세스 키 ID를 복사하여 입력해준다. 입력하면 비밀 액세스 키도 입력하라고 한다. 비밀 액세스 키를 복사하여 입력해준다.
>
> ![ID](https://user-images.githubusercontent.com/94885063/144751201-13555020-1521-4fdd-9984-01c115eb8107.PNG)
>
> ##### 다음으로 지역을 입력하라고 나오는데 우리의 지역은 서울이므로 'ap-northeast-2'를 복사해서 입력한다. 그후 output format을 입력하라고 하는데 json을 많이 사용하므로 josn을 입력한다. 해당 과정을 마무리 하면 다음 사진과 같이 폴더가 새로 생성된 것을 확인할 수 있다.
> 
> ![파일](https://user-images.githubusercontent.com/94885063/144751298-0ec7146a-03d3-4844-85c2-81842d4ae155.PNG)

### Boto3 설치 방법
> Boto3는 pytho 3.6버전 이상부터 사용된다.
~~~
python3 --version
~~~
> 이 코드를 통해 현재 파이썬 버전을 확인한다. 3.6이상이 아니라면 업데이트를 필수로 해야한다. 3.6이상 버전인 것을 확인했다면 설치를 시작한다.
~~~
sudo pip3 install boto3
~~~
>
> ![boto3](https://user-images.githubusercontent.com/94885063/144751458-b585d97d-3eb7-43ea-91a1-115a6cb8db16.PNG)
>
> 터미널에 사진과 같은 상황을 통해 설치가 완료된다.

### Open CV 설치 방법
> 1.이전 버전의 Open CV 제거하기
>
> Open CV 이전 버전이 설치되어 있으면 새로 설치하는 Open CV 4.5.1 버전이 제대로 동작하지 않기 때문에 제거해주어야한다.
~~~
pkg-config --modversion opencv
~~~
> 이 코드를 통해 현재 Open CV 버전을 확인한다. 만약 설치가 되어 있지 않다면 설치를 해주어야한다.(이 경우 2번을 진행한다.) 이전 버전이 설치되어 있는 경우라면 삭제한다. 
> 
> ![opencv](https://user-images.githubusercontent.com/94885063/144751656-330cebf2-7e47-4281-91f0-f56111a79da2.PNG)
>
> ###### ↑설치가 되어있지 않은 상태의 경우 나오는 문구.
>
> 다음 명령으로 Open CV 라이브러리 설정 파일을 포함해서 기존에 설치된 Open CV 패키지를 삭제하고 진행해야 합니다. 코드는 아래와 같습니다.
~~~
sudo apt-get purge  libopencv* python-opencv
~~~
~~~
sudo apt-get autoremove
~~~
> 컴파일하여 설치한 경우 다음처럼 Open CV 버전을 확인할 수 있습니다. 
~~~
pi@raspberrypi:~ $ pkg-config --modversion opencv

3.4.2
~~~
> 또는
~~~
pi@raspberrypi:~ $ pkg-config --modversion opencv4

4.5.1
~~~
> 다음 명령으로 기존에 설치된 Open CV 라이브러리를 삭제합니다.
~~~
sudo find /usr/local/ -name "*opencv*" -exec rm  {} \;
~~~
> 2.기존 설치된 패키지 업그레이드
> OpenCV 4.5.1을 설치해주기 전에 기존에 설치된 패키지들을 업그레이드 해주기 위한 작업입니다. 
>
> 기존에 설치된 패키지의 새로운 버전이 저장소에 있다면 패키지 리스트를 업데이트합니다. 
~~~
sudo apt update 
~~~
> 이후 재부팅을 해줍니다.
~~~
sudo reboot
~~~
> 3.Open CV 컴파일 전 필요한 패키지 설치
> Open CV를 컴파일하는데 필요한 패키지들을 설치합니다.
>
> build-essential 패키지에는 C/C++ 컴파일러와 관련 라이브러리, make 같은 도구들이 포함되어 있습니다.
>
> cmake는 컴파일 옵션이나 빌드된 라이브러리에 포함시킬 OpenCV 모듈 설정등을 위해 필요합니다. 
~~~
sudo apt install build-essential cmake
~~~
> 특정 포맷의 이미지 파일을 불러오거나 저장하기 위해 필요한 패키지들입니다.
~~~
sudo apt install libjpeg-dev libtiff5-dev libjasper-dev libpng-dev
~~~
> 특정 코덱의 비디오 파일/스트리밍을 읽어오거나 기록하기 위해 필요한 FFmpeg 관련 패키지들입니다.
~~~
sudo apt install libjpeg-dev libtiff5-dev libjasper-dev libpng-dev
~~~
> Video4Linux 패키지는 리눅스에서 실시간 비디오 캡처를 지원하기 위한 디바이스 드라이버와 API를 포함하고 있습니다. 
~~~
sudo apt install libv4l-dev v4l-utils
~~~
> 특정 코덱의 비디오 파일/스트리밍을 읽어오거나 기록하기 위해 필요한 GStreamer 관련 패키지들입니다.
~~~
sudo apt install libgstreamer1.0-dev libgstreamer-plugins-base1.0-dev gstreamer1.0-plugins-good gstreamer1.0-plugins-bad gstreamer1.0-plugins-ugly
~~~
> OpenCV에서는 highgui 모듈을 사용하여 자체적으로 윈도우 생성하여 이미지나 영상을 보여줍니다.  
>
> 윈도우 생성 등의 GUI를 위해 gtk 또는 qt를 선택해서 사용가능합니다.  본 글에서는  gtk2를 사용합니다. 
~~~
sudo apt install libgtk2.0-dev
~~~
> OpenGL 지원하기 위해 필요한 라이브러리입니다.
~~~
sudo apt install mesa-utils libgl1-mesa-dri libgtkgl2.0-dev libgtkglext1-dev
~~~
> OpenCV 최적화를 위해 사용되는 라이브러리들입니다.
~~~
sudo apt install libatlas-base-dev gfortran libeigen3-dev
~~~
> python2.7-dev와 python3-dev 패키지는 파이썬을 위한 헤더파일과 라이브러리가 포함된  패키지들입니다. 
>
> Numpy는 매트릭스 연산등을 빠르게 처리할 수 있어서 OpenCV Python에서 사용됩니다.
~~~
sudo apt install python3-dev python3-numpy
~~~
> 4.Open CV 설정과 컴파일 및 설치
> 소스 코드를 저장할 임시 디렉토리를 생성하여 이동 후 진행합니다
~~~
mkdir opencv
~~~
~~~
cd opencv
~~~
~~~
~/opencv $
~~~
> OpenCV 4.5.1 소스코드를 다운로드 받아 압축을 풀어줍니다.
~~~
wget -O opencv.zip https://github.com/opencv/opencv/archive/4.5.1.zip
~~~
~~~
unzip opencv.zip
~~~
> opencv_contrib(extra modules) 소스코드를 다운로드 받아 압축을 풀어줍니다.
>
> SURF 등을 사용하기 위해 필요합니다.
~~~
wget -O opencv_contrib.zip https://github.com/opencv/opencv_contrib/archive/4.5.1.zip
~~~
~~~
unzip opencv_contrib.zip
~~~
> 다음처럼 두 개의 디렉토리가 생성됩니다.
~~~
pi@raspberrypi:~/opencv $  ls -d */

opencv-4.5.1  opencv_contrib-4.5.1
~~~
> opencv-4.5.1 디렉토리로 이동하여 build 디렉토리를 생성하고 build 디렉토리로 이동합니다.
>
> 컴파일은 build 디렉토리에서 이루어집니다.
~~~
pi@raspberrypi:~/opencv $  cd opencv-4.5.1

pi@raspberrypi:~/opencv/opencv-4.5.1 $  mkdir build

pi@raspberrypi:~/opencv/opencv-4.5.1 $  cd build

pi@raspberrypi:~/opencv/opencv-4.5.1/build $  
~~~
> cmake를 사용하여 OpenCV 컴파일 설정을 해줍니다.  복사해서 터미널에 붙여넣기 해주면 됩니다. 
>
> ![OPENCV1](https://user-images.githubusercontent.com/94885063/144752354-93af0660-0c79-4764-bbfd-c7ddf13f4b5d.PNG)
~~~
cmake -D CMAKE_BUILD_TYPE=RELEASE -D CMAKE_INSTALL_PREFIX=/usr/local -D WITH_TBB=OFF -D WITH_IPP=OFF -D WITH_1394=OFF -D BUILD_WITH_DEBUG_INFO=OFF -D BUILD_DOCS=OFF -D INSTALL_C_EXAMPLES=ON -D INSTALL_PYTHON_EXAMPLES=ON -D BUILD_EXAMPLES=OFF -D BUILD_TESTS=OFF -D BUILD_PERF_TESTS=OFF -D ENABLE_NEON=ON -D ENABLE_VFPV3=ON -D WITH_QT=OFF -D WITH_GTK=ON -D WITH_OPENGL=ON -D OPENCV_ENABLE_NONFREE=ON -D OPENCV_EXTRA_MODULES_PATH=../../opencv_contrib-4.5.1/modules -D WITH_V4L=ON -D WITH_FFMPEG=ON -D WITH_XINE=ON -D ENABLE_PRECOMPILED_HEADERS=OFF -D BUILD_NEW_PYTHON_SUPPORT=ON -D OPENCV_GENERATE_PKGCONFIG=ON ../
~~~
> 다음처럼 cmake 실행 중에  추가적인 다운로드가  있습니다.  
>
> 라즈베리파이에 인터넷이 연결된 상태에서 진행하세요.
~~~
-- xfeatures2d/boostdesc: Download: boostdesc_bgm.i

-- xfeatures2d/boostdesc: Download: boostdesc_bgm_bi.i

-- xfeatures2d/boostdesc: Download: boostdesc_bgm_hd.i

-- xfeatures2d/boostdesc: Download: boostdesc_binboost_064.i

-- xfeatures2d/boostdesc: Download: boostdesc_binboost_128.i

-- xfeatures2d/boostdesc: Download: boostdesc_binboost_256.i

-- xfeatures2d/boostdesc: Download: boostdesc_lbgm.i

-- xfeatures2d/vgg: Download: vgg_generated_48.i

-- xfeatures2d/vgg: Download: vgg_generated_64.i

-- xfeatures2d/vgg: Download: vgg_generated_80.i

-- xfeatures2d/vgg: Download: vgg_generated_120.i

-- data: Download: face_landmark_model.dat
~~~
> 다음과 같은 메시지가 보이면 정상적으로 된 것입니다.
~~~
-- Configuring done

-- Generating done

-- Build files have been written to: /home/pi/opencv/opencv-4.5.1/build
~~~
> 진행하기 전에 스왑(swap) 공간을 늘려줘야 멀티코어를 사용하여 컴파일시 메모리 부족으로 에러가 나지 않습니다.
>
> /etc/dphys-swapfile 파일을 열어서 
~~~
sudo nano /etc/dphys-swapfile
~~~
> CONF_SWAPSIZE  변수값을 100에서 2048로 수정합니다.
>
> Ctrl + O를 눌러 저장하고 Ctrl + X를 눌러 빠져나옵니다. 
>
> 스왑 서비스 재시작하여 변경된 설정을 반영시켜주면 스왑 크기가 대략 20배가 됩니다. 
> 
> 스왑 관련 서비스를 재시작합니다
~~~
pi@raspberrypi:~/opencv/opencv-4.5.1/build $ sudo /etc/init.d/dphys-swapfile restart

[ ok ] Restarting dphys-swapfile (via systemctl): dphys-swapfile.service.
~~~
> 이제 make 명령을 사용하여 컴파일을 시작합니다.  -j4 옵션을 주고 우선 진행합니다.
~~~
time make -j4
~~~
> 이제 컴파일 결과물을 설치합니다. 샘플 코드들은 /usr/local/share/opencv4/samples/ 위치에 복사됩니다. 
~~~
sudo make install
~~~
> opencv  라이브러리를 찾을 수 있도록 다음 명령을 실행합니다.
~~~
sudo ldconfig
~~~
> /etc/dphys-swapfile 파일을 열어서 
~~~
sudo nano /etc/dphys-swapfile
~~~
> CONF_SWAPSIZE  변수값을 다시 100으로 수정합니다.
>
> 스왑 서비스 재시작하여 변경된 설정을 반영시켜주면 스왑 크기가 원래대로 돌아옵니다. 
~~~
pi@raspberrypi:~/opencv/opencv-4.5.1/build $ sudo /etc/init.d/dphys-swapfile restart

[ ok ] Restarting dphys-swapfile (via systemctl): dphys-swapfile.service.
~~~
> 5.Open CV 설치 결과 확인
> 
> python 3에서 opencv 라이브러리를 사용가능한지는 확인합니다.
>
> OpenCV 버전이 출력되어야 합니다.
~~~
pi@raspberrypi:~/opencv/opencv-4.5.1/build $ python3

Python 3.9.2 (default, Mar 12 2021, 04:06:34)

[GCC 10.2.1 20210110] on linux

Type "help", "copyright", "credits" or "license" for more information.

>>> import cv2

>>> cv2.__version__

'4.5.1'

>>> quit()
~~~
> 예제 코드를 실행해봅니다. 웹캠 영상을 볼 수 있습니다. 
~~~
$ python3 /usr/local/share/opencv4/samples/python/video.py
~~~
> 다음과 같은 에러가 발생합니다.
~~~
[ WARN:0] global /home/pi/opencv/opencv-4.5.1/modules/videoio/src/cap_gstreamer.cpp (935) open OpenCV | GStreamer warning: Cannot query video position: status=0, value=-1, duration=-1
~~~
> 수정해봅시다. OpenCV에서 있는 것으로 진행해보았습니다. video.py 파일을 열어서 
~~~
$ nano video.py
~~~
> 188번째 줄을 다음처럼 수정합니다. Ctrl + O로 저장후, Ctrl + X로 종료합니다.
~~~
cap = cv.VideoCapture(source, cv.CAP_V4L2)
~~~
> 실행해봅니다. 경고문 없이 영상이 바로 보입니다. 
~~~
$ python video.py

$ python3 video.py
~~~
> 이제 필요 없어진 컴파일에 사용했던 opencv 소스코드 디렉토리를 삭제합니다.
~~~
pi@raspberrypi:~/opencv/opencv-4.5.1/build $ cd

pi@raspberrypi:~ $ rm -rf opencv
~~~
