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
