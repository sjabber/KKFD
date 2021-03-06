SELECT * FROM kk_member;
SELECT * FROM kk_creator;
SELECT * FROM kk_project;
SELECT * FROM kk_funding;
SELECT * FROM kk_bookmark;

--DELETE FROM kk_project;
--DELETE FROM kk_creator;
--DELETE FROM kk_member;
--DELETE FROM kk_funding;
--DELETE FROM kk_bookmark;

INSERT INTO kk_member VALUES ('id1', 'pw1', '김서은', '01059125166', 'id1@id1.com');
INSERT INTO kk_member VALUES ('id2', 'pw2', '이서금', '01085451079', 'id2@id2.com');
INSERT INTO kk_member VALUES ('id3', 'pw3', '박서서', '01025750967', 'id3@id3.com');
INSERT INTO kk_member VALUES ('id4', 'pw4', '최서중', '01075777908', 'id4@id4.com');
INSERT INTO kk_member VALUES ('id5', 'pw5', '유재석', '010-1111-1111', 'you@gmail.com');
INSERT INTO kk_member VALUES ('id6', 'pw6', '박명수', '010-2222-2222', 'gpark@gmail.com');
INSERT INTO kk_member VALUES ('id7', 'pw7', '정준하', '010-3333-3333', 'middle@gmail.com');
INSERT INTO kk_member VALUES ('id8', 'pw8', '하하', '010-4444-4444', 'haha@gmail.com');
INSERT INTO kk_member VALUES ('classyart', 'pw', '정지수', '010-4444-4445', 'jjss01@naver.com');
INSERT INTO kk_member VALUES ('freesia', 'pw', '김성수', '010-4444-4446', 'sshh02@naver.com');
INSERT INTO kk_member VALUES ('id9', 'pw9', '유해진', '010-9999-1111', 'sea@gmail.com');
INSERT INTO kk_member VALUES ('id10', 'pw10', '송강호', '010-2222-3333', 'song@gmail.com');
INSERT INTO kk_member VALUES ('id11', 'pw11', '송중기', '010-3333-4444', 'vin@gmail.com');

INSERT INTO kk_creator VALUES ('id1', '신이어마켓', '소개1', '김서은', '국민', '1111-1111-1111');
INSERT INTO kk_creator VALUES ('id2', '아뜰리에페이', '소개2', '이서금', '우리', '2222-2222-2222');
INSERT INTO kk_creator VALUES ('id3', 'LouisNeuf', '소개3', '박서서', '농협', '3333-3333-3333');
INSERT INTO kk_creator VALUES ('id4', '미소', '소개4', '최서중', '신한', '4444-4444-4444');
INSERT INTO kk_creator VALUES ('id5', '비누좋아', '소개5', '유재석', '국민', '1111-1111-1111');
INSERT INTO kk_creator VALUES ('id6', '룰루랄라', '소개6', '박명수', '우리', '2222-2222-2222');
INSERT INTO kk_creator VALUES ('id7', '오랜기다림', '소개7', '정준하', '농협', '3333-3333-3333');
INSERT INTO kk_creator VALUES ('id8', '피부케어', '소개8', '하하', '신한', '4444-4444-4444');
INSERT INTO kk_creator VALUES ('classyart', '클래시아트', '소개9', '정지수', '신한', '5555-5555-5555');
INSERT INTO kk_creator VALUES ('freesia', '프리지아', '소개10', '김성소', '신한', '6666-6666-6666');
INSERT INTO kk_creator VALUES ('id9', '자연은', '소개5', '노홍철', '산업', '5555-5555-5555');
INSERT INTO kk_creator VALUES ('id10', 'EM', '소개6', '길', '하나', '6666-6666-6666');
INSERT INTO kk_creator VALUES ('id11', '마마아이', '소개7', '데프콘', '우리', '7777-7777-7777');

INSERT INTO kk_project VALUES ('7', 'id4', '5', '새로운 연결, <생명평화 멋단추>', '생명평화 멋단추는 생명평화무늬를 누구나 편하게 지니고 다닐 수 있도록 만들어진 프로젝트', '생명평화 멋단추는 생명평화무늬를 누구든 편하게 지니고 다닐 수 있도록 만들어진 프로젝트입니다. 이 무늬를 지니고 다니며 주변에 생명이 있는 존재들을 돌아보고, 그들의 평화를 기원하는 마음을 담아 기획했습니다.생명평화무늬란 온 우주의 생명이 하나로 연결되어 서로가 서로에게 존재의 근원이 되고 의지하며 살아가고 있다는 "생명"의 뜻과, 연결되어 있는 생명들이 평화롭게 함께 잘 살기 위해서는 세상의 평화를 원한다면 내가 먼저 평화가 되자는 “평화”의 뜻을 동시에 담고 있습니다. 이 무늬는 현재 생명평화운동의 상징으로 사용되고 있으며, 날개 *디자이너 안상수가 2005년에 *멋지었습니다.', '23000', '1000', '50', '0', '0', '2021/07/01', '2021/07/18', '2021/08/17', '0', '1');
INSERT INTO kk_project VALUES ('8', 'id4', '5', '테이블을 특별하게 만들어줄 영국감성 TEA TIME KIT', '스콘홀더, 앞치마, 키친크로스, 티코스터와 함께 특별한 티타임을 즐겨보세요.', '매일 반복되는 일상 속에서 작은 변화가 생긴다면 쉽게 행복해질 수 있지 않을까요? 일상을 근사하게 만들어줄 작은 변화는 무엇일까? 여기서부터 첫번째 프로젝트가 시작되었습니다. 매일 반복되는 일상 속에서 변화를 꿈꾸는 이들을 위해 선물을 준비했습니다. 첫번째 프로젝트를 구성하면서 플림의 제품이 누군가에게 행복의 원천이 되길 바라는 마음을 담았습니다. 바쁜 일상 속에서 가지는 잠깐의 티타임이, 베이킹을 하는 순간이, 소중한 사람과 함께하는 피크닉이 FLIM의 티 타임 키트 TEA TIME KIT 를 통해 더 즐겁게 다가가길 바랍니다. 집에서 보내는 시간이 늘어나면서 취미로 베이킹을 하게 되었습니다. 직접 구운 디저트를 가족, 친구들과 커피와 티를 마시며 즐기는 시간이 큰 행복으로 자리잡았습니다. 베이킹 시간과 티 타임을 가끔은 특별하게 즐기고 싶다는 생각이 들었습니다.', '75000', '200', '50', '0', '0', '2021/07/04', '2021/08/09', '2021/09/08', '0', '1');
INSERT INTO kk_project VALUES ('9', 'id4', '5', '당신을 지켜주는 영물, 메롱해치 자수 KIT', '메롱해치 자수 키트는 쉽게 프랑스 자수를 배울 수 있는 키트입니다.', '1가구 1메롱해치가 무슨무슨 법으로 정해진 게 진짜인가요? 2021년 액운을 쫓고 든든하게 나를 지켜줄 메롱해치 수호신 데려가세요! "나는 왜 이렇게 되는 일이 없지?" "속마음 툭 털어놓고 싶은데 누구한테 해야 하나..." 보디가드처럼 나를 지켜주고 친한 친구보다 내 마음을 잘 알아주는 걱정 인형보다 더 든든한 수호신이 필요하지 않으세요?   마음을 차분하게 정리하며 힐링을 취미 자수로 나만의 수호신 해치를 수놓으면 어떨까요? 예로부터 해치는 액운을 막아준다는 영물인데요,  자수로 한 땀 한 땀 해치를 수놓아 인테리어 소품으로 활용하거나 브로치로 만들어 내가 가는 곳 어디든 함께 데리고 가보세요. 어떻게 해야 할지 모른다고요?  <메롱해치 자수 키트> 하나면 문제 없습니다.  천천히 손가락을 놀려 혀를 쏙 내밀고 있는 귀여운 해치를 수놓고 소원을 빌어보세요. 누가 알겠어요. 여러분의 마음을 메롱해치가 토닥토닥 쓰다듬어줄지', '34000', '200', '50', '0', '0', '2021/07/07', '2021/08/17', '2021/09/16', '0', '1');
INSERT INTO kk_project VALUES ('10', 'id4', '5', '포근한 겨울 준비를 위한 메리노 양모 자이언트 담요', '메리노 양모로 만든 자이언트 담요입니다.', ' 따땃의 자이언트 담요는 100% 메리노 양모로 만들어진 매우 부드럽고 포근한 담요입니다.  메리노 양에서 채취한 양모는 양털 중 가장 부드럽기로 유명합니다. 메리노 양모는 훌륭한 자동 온도 조절 특성을 지니고 있어, 내구성이 좋아 따뜻하고 공기의 순환이 좋다는 장점을 갖고 있습니다. 또한 100% 메리노 양모는 피부를 자극하지 않고 부드럽기 때문에 아이들과 애완동물까지 안전하고 포근하게 따뜻함을 즐길 수 있습니다. 따땃의 자이언트 담요는 아란 스티치를 활용하여 제작한 담요입니다.  아란 스티치는 아일랜드 서쪽 해상의 아란 제도에서 대를 이어 전해져 내려오는 손뜨개 방식으로 여러 가지 의미를 담은 아란 특유의 스티치 패턴입니다. 이번 프로젝트에서 기본 짜임과 같이 소개할 패턴은 풍요와 성장을 의미하는 "Irish Moss (이끼) 패턴"입니다. 아이리시 해안에서 자라는 이끼는 불모의 땅에서 귀한 음식과 비옥한 비료로 사용되었기 때문에 풍부한 성장과 좋은 수확의 의미를 가지게 되었습니다.', '68200', '200', '50', '0', '0', '2021/07/10', '2021/09/09', '2021/10/09', '0', '1');
INSERT INTO kk_project VALUES ('11', 'id4', '5', '이제 그만 자수하세요. 전통 자수 DIY 키트', '국가 무형문화재 제 80호 자수장 이수자 윤정숙 장인과 함께한 전통 자수 DIY 키트', '[국가 무형문화재 제 80호 자수장 이수자 윤정숙 작품] 바늘과 실로 한국적인 아름다움을 표현한 것이 전통 자수입니다.  전통 자수는 실의 오묘한 결과 색상의 아름다움을 느낄 수 있으며, 복식, 병풍, 장, 베게, 이불 등 실생활을 아름답게 장식해 주었기 때문에   남녀노소 가리지 않고 대대로 사랑받아 왔습니다.    풍경, 자연물, 동물 등을 섬세하고, 사실적으로 묘사하는 것이 특징입니다.  결을 살린 섬세한 바느질로 명암과 음영까지 표현합니다.  또한 먼지 떨기 김 쐬기 풀질하기 등 서양자수에서는 볼 수 없는 후처리 과정이 뒤따르기 때문에 매우 완성도 높은 작품이 됩니다. 다양한 기법으로 표현하는 전통 자수만의 아름다움을 경험해보세요!', '28000', '3000', '100', '0', '0', '2021/07/13', '2021/08/01', '2021/08/31', '0', '1');
INSERT INTO kk_project VALUES ('12', 'id2', '2', '[아기냄새] 나만 알고 싶은 살구빛 베이비파우더', '재단하지 않은 장미의 아름다움을 담은 들장미향과 자연스러운 살냄새를 담은 향수', '보송보송 보드라운 솜털, 포동포동 살이 오른 살구빛 아기의 볼 입안 가득 퍼지는 달콤하고 상큼한 살구의 맛 순수하고 폭닥한 베이비 파우더와 상큼달달한 살구의 향의 조합 그 사랑스러운 달콤함을 당신께 드립니다.Ξ 이런 분들께 추천해요. 베이비파우더 향이 강한 기존의 아기 냄새 향수가 지루하게 느껴지는 분 보송보송한 아기의 향을 유지하면서도 상큼하고 달콤한 향을 느끼고 싶은 분 나만의 아기 향수를 찾으시는 분 살구의 생기 넘치는 빛깔과 달달한 맛, 사랑스러운 향을 좋아하시는 분 아기들의 순수하고 달큼한 냄새가 그리운 분들Ξ 선물안내 - 용량 : 30ml - 부향률 : 7% / 아기의 냄새를 표현하기 위해 은은한 느낌을 줄 수 있는 방향으로 설정했습니다. 추후 후원자 여러분들의 의견에 따라 약간의 변동폭은 있습니다', '33500', '1000', '50', '0', '0', '2021/07/16', '2021/08/17', '2021/09/16', '0', '1');
INSERT INTO kk_project VALUES ('13', 'id2', '2', '#상큼 #청량 #리프레쉬, 시트러스 아로마틱 향수', '보송보송 보드라운 솜털, 포동포동 살이 오른 살구빛 아기의 볼, 그 사랑스러움을 담은 향수', '참 신기하게도 계절에 따라 상황에 따라 또 옷차림에 따라 선호하고 어울리는 향이 달라지잖아요. 추워지면 따뜻하고 조금은 무거운 느낌의 향을 찾다가 날씨가 풀리기 시작하면 계절에 어울리게 상쾌하고 가벼운 느낌의 향을 찾게 되더라고요. 그래서 겨우내?가라앉았던 기분을 리프레시 시켜줄 수 있는 상쾌한 향을 준비했습니다. 시트러스는 레몬, 귤, 오렌지, 자몽, 라임 등과 같이 상큼하고 싱그러운 느낌을 주는 향인데요. 이런?시트러스 향에 아로마 향취를 더해 더 트렌디하고 또 편안하게 사용하실 수 있는 시트러스 아로마틱 향수로 찾아뵙게 되었습니다.?', '60000', '2000', '100', '0', '0', '2021/07/19', '2021/08/19', '2021/09/18', '0', '1');
INSERT INTO kk_project VALUES ('14', 'id2', '2', '[독보적잔향] 모네의 들꽃 정원을 담은 향수', '모네의 들꽃 정원을 그린 플로럴 우디 타입의 향수입니다.', '자연스러운 아름다움 vs 만들어진 아름다움, 둘 중에 어떤 아름다움이 더 끌리시나요? 화려하게 포장되어 날 데려가~ 하고 외치는 꽃집의 꽃다발도 너무나 예쁘지만, 포장지로 꾸며내지 않아도 그저 있는 그대로 나 여기 있어~ 하고 존재감을 뿜어내는 수수한 들꽃의 아름다움은 우리 모두에게 커다란 감동을 건네죠.', '54900', '1000', '100', '0', '0', '2021/07/22', '2021/08/06', '2021/09/05', '0', '1');
INSERT INTO kk_project VALUES ('15', 'id3', '2', '빵순이 마녀의 달콤한 유혹,헨젤과 그레텔 with 버터크림', '부드러운 버터와 달콤한 크림이 가득한 빵과 케이크, 헨젤과 그레텔을 위해 만든 마녀의 향기', '우리가(빵순이들이...) 매일 접하는 빵과 케이크, 구움과자 등을 떠올려보면?숨막힐듯한 버터향과 무조건 먹어줘야 하는 부드러운 동물성(꼭 동물성..)크림의 달콤한 맛들이 대부분입니다. 저의 빵킷리스트에도 그러한 곳들이 많구요, 마녀 역시 이 버터와 크림으로 헨젤과 그레텔 남매를 유인할 집을 지은것으로 보입니다.?위와 같이 부드러운 버터의 풍미가 살아있는 것과 달콤한 크림과 과일이 올라간 것들로 분류가 되었어요.이 분류에 따라서 이번 향기의 메인 재료는 버터와 크림으로 정했고 버터와 잘어울리는 밀크, 크림과 잘 어울리는 베리를 부재료로 포뮬러를 작성하였습니다. (조향_포뮬러 = 레시피) 조향에는 메인이 되는 기조제와 그에 변화를 주는 조화제가 필요한데 빵을 구성하는 재료 역시 향과 같이 이렇게 매칭을 할 수 있답니다. 오빠인 헨젤은 버터밀크(왕댜...), 동생인 그레텔은 크림베리(곤듀...) 컨셉으로 구성해 보았습니다.', '29500', '3000', '100', '0', '0', '2021/07/25', '2021/08/12', '2021/09/11', '0', '1');
INSERT INTO kk_project VALUES ('16', 'id3', '2', '코코넛 럼 향기 가득, 달콤한 파인애플 칵테일 #피나콜라다', '부드러운 코코넛 럼과 달코~옴한 파인애플이 만나 세상 둘도 없는 피나콜라다가 되었습니다.', '안녕하세요, 미혹의 향기를 만들어내는 조향사 루이스네프입니다.파인애플 향을 처음 만들었던 때에는 이렇게 계속 해마다 만들게 될지 몰랐는데, 저와 가까운 분들이 날씨가 더워질듯 하면 자꾸 재촉하는 바람에 같은 것은 만들기 싫고해서 사실 해마다 다른 파인애플을 만들게 되었답니다.그랬던, 파인애플 시리즈가 올해는 제가 자발적으로 새로운 버전을 만들게 되었습니다. 때는 바야흐로 애지중지 공들인 민트초코 프로젝트가 끝나고 며칠 지나지 않아서입니다. 또 새로운 무언가를 만들기 위해서 바(Bar)에 앉아서 칵테일을 마시는데 메뉴에 피나콜라다가 눈에 들어오는겁니다! 저의 여름 숙제와도 같은 파인애플이 들어가는 클래식한 칵테일이라고, 코코넛 밀크와 럼이 베이스가 되는 아름다운 녀석이 있었습니다.', '29500', '1500', '100', '0', '0', '2021/07/28', '2021/08/24', '2021/09/23', '0', '1');
INSERT INTO kk_project VALUES ('17', 'id3', '2', '세상을 지배할 민초단의 심장, 민트로얄(MintRoyal)', '민초단의 심장을 뛰게할 향기! 달콤하고 진한 초콜릿과 시원한 페퍼민트의 청량감을 느껴보세요', '저는 찐 민초단입니다 여러분, 언젠가는 이런 날이 올 줄 알았습니다.치약맛 나는 것을 왜 먹냐, 파란색은 먹는게 아니라는 반민초파 지인들의 비디오, 오디오 지원되는 듯한 리액션을 꾹 참고 지내온 나날들.... 하지만 이제는 민트 도넛, 꽈배기, 민트 빙수, 민트 초콜릿, 민트 케이크 등등! 다양한 민초들이 세상을 지배하고 있고 언제 어디서나 마음껏 즐길 수 있습니다! ㅠ_   ㅠ그리고 민초에 진심인 조향사는 마음껏 먹고 마시며(?) 탐닉한 결과, 민트 초코를 주제로 이렇게 올해의 첫 프로젝트를 여러분께 선보이게 되었습니다', '27500', '1500', '100', '0', '0', '2021/07/31', '2021/08/24', '2021/09/23', '0', '1');
INSERT INTO kk_project VALUES ('18', 'id5', '3', '어성초비누', '#건성피부 #피부진정 #깊은보습', '피부가 약하거나 아토피성 질환이 있을 경우 얼굴과 몸에 화장수로 사용하면 효과가 좋습니다.', '5000', '1000', '1500', '500', '50', '2021-08-02', '2021-10-01', '2021-10-16', '9', '1');
INSERT INTO kk_project VALUES ('19', 'id5', '3', '유황비누', '#보습관리 #피부탄력 #모공청소', '피부병 치료와 피부 보호작용을 하는 물질인 유황을 활용한 비누입니다.', '6000', '1000', '1500', '500', '50', '2021-08-02', '2021-10-01', '2021-10-16', '9', '1');
INSERT INTO kk_project VALUES ('20', 'id5', '3', '딸기비누', '#지성피부 #피지조절 #어두운안색', '딸기에 함유된 성분들이 과잉피지 및 유수분 관리를 해주어 피부를 맑고 깨끗하게 가꿔줍니다.', '4000', '1000', '1500', '500', '50', '2021-08-03', '2021-10-02', '2021-10-17', '10', '1');
INSERT INTO kk_project VALUES ('21', 'id6', '3', '알로에비누', '#건성피부 #피부진정 #깊은보습', '피지분비 정상화 및 피부미백에 효과가 있습니다.', '5000', '1000', '1500', '400', '40', '2021-08-04', '2021-10-03', '2021-10-18', '4', '1');
INSERT INTO kk_project VALUES ('22', 'id6', '3', '녹차비누', '#지성피부 #피지조절 #어두운안색', '노폐물과 피지 제거, 피부 탄력에 도움을 줍니다.', '6000', '1000', '1500', '400', '40', '2021-08-05', '2021-10-04', '2021-10-19', '2', '1');
INSERT INTO kk_project VALUES ('23', 'id6', '3', '아토비누', '#보습관리 #피부탄력 #모공청소', '보습, 피부진정, 민감한 피부에 사용하기 좋습니다.', '8000', '1000', '1500', '400', '40', '2021-08-06', '2021-10-05', '2021-10-20', '16', '1');
INSERT INTO kk_project VALUES ('24', 'id7', '4', '직사각접시', '#전통 #고급', '고급호텔 한정식 테이블웨어 그대로 즐길 수 있는 상품입니다.', '4000', '1000', '1500', '1300', '130', '2021-08-16', '2021-10-15', '2021-10-30', '16', '1');
INSERT INTO kk_project VALUES ('25', 'id8', '4', '앞접시', '#귀여운 #캐릭터', '캐릭터를 이용하여 제작된 앞접시로 감성적인 느낌을 줍니다.', '4000', '1000', '1500', '1300', '130', '2021-08-17', '2021-10-16', '2021-10-31', '73', '1');
INSERT INTO kk_project VALUES ('26', 'id9', '4', '원형접시', '#고급 #전통', '멋스러운 그릇에 계절과 상관없이 어떤 음식이든 담아 사용하기 좋은 접시입니다.', '6000', '1000', '1500', '700', '70', '2021-08-18', '2021-10-17', '2021-11-01', '23', '1');
INSERT INTO kk_project VALUES ('27', 'id10', '4', '나뭇잎', '#전통 #디자인', '나뭇잎 모양의 방짜유기로 식탁에 올리는 것 자체만으로도 포인트가 됩니다.', '5000', '1000', '1500', '700', '70', '2021-08-19', '2021-10-18', '2021-11-02', '47', '1');
INSERT INTO kk_project VALUES ('28', 'id11', '4', '티트레이', '#파티 #디저트', '완벽한 디저트 타임을 만들어줄 철제 티트레이 세트입니다.', '4000', '1000', '1500', '700', '70', '2021-08-20', '2021-10-19', '2021-11-03', '55', '1');


INSERT INTO kk_project VALUES ('1', 'classyart', '1', '[클래시아트] 취미로의 첫 여행 : 투톤 젤캔들만들기 키트', '클래시아트가 준비한 첫번째 취미키트! 러블리 뿜뿜! 쿨내 뿜뿜! 젤캔들을 내 방에서 만들기', '무미건조한 일상에 건강한 취미 한 끼
부러우면 치는거야, 취미브런치 by. 클래시아트

안녕하세요 클래시아트입니다.
반복되는 일상
하기 싫은 일을 하며 혹 시키는 일을 하며 하루를 버티고 계시진 않나요?
아니면 포부를 갖고 하고 싶은 일을 하겠다며 나섰다가
"어라?!"
바쁜 일상을 살다가 잠시 짬이 나거나 휴일이 주어지면 무엇을 해야할 지 몰라 방황하셨던 분들!
이력서 ‘취미란‘에 ‘독서‘나 ‘영화감상‘을 써놓거나 망설이셨던 분들!
이제 클래시아트와 함께 하세요!
클래시아트와 무미건조한 일상에 건강한 취미 한 끼 하실래요?
그 첫 번째 여행이 시작됩니다.
내 방구석으로 부터
마음의 안식처로 떠나는 취미여행.
그 첫번째 키트는 세젤 쉽고 세젤 예쁜 젤키트
출-퇴근, 집-학교, 반복되는 일상에 지쳐서 여행을 떠나고 싶어도
남아있는 휴가일도 없고 휴가 갈 돈도 부족하다면?
젤 캔들로 퐈이아!
불타오르네(feat.BTS) 불태웁시다.

*주의사항
1. 반려동물이나 어린 아이의 손이 닿지 않는 곳에 보관하십시오
2. 향초를 장시간 사용은 피하고, 향초를 켜둔 상태에서 외출 및 취침은 피하십시오
3. 절대 화재가 발생할만한 물건의 가까운 곳에서 사용은 피하십시오
4. 과열을 방지할 수 있는 제품의 캡이나 받침대를 사용하시고 향초 왁스가 1cm 미만 남았을 시에는 사용을 중단하십시오
5. 사용방법및 주의사항을 따르지 않았을 시 재산상의 피해를 입을 수도 있으며 절대 책임지지 않습니다
취미 여행은 즐거우셨나요?
이제 우리의 앞날을 밝혀보아요. ', '39000', '100', '500', '120', '0', '2021/08/18', '2021/09/18', '2021/10/18', '221', '1');

-----------------------------------------------------------------------------------

INSERT INTO kk_project VALUES ('2', 'classyart', '1', '유기견들의 겨울을 밝히는 ‘소이 메시지캔들’', '강제철거와 늘어난 유기견들로 인해 운영에 어려움을 겪고 있는 ‘내사랑 바둑이‘를 도와주세요', '"유기견들의 겨울을 밝히는 초"
저희 “길보듬”은 새 보금자리에서 겨울나기를 준비하는 유기견들을 후원해주시는 분들을 위해  ‘소이 메시지 캔들’을 리워드로 준비했습니다.
캔들 앞면엔 , 저희 ‘길보듬’ 로고가 그려진 스티커가 부착되어 있어요. 향초의 불빛을 상징하는 빨간 목도리가 강아지 목에 둘러져 있는 모습이에요. 시리고 춥고 그리고 많이 두려울 아이들에게 따뜻한 겨울을 선물해주고 싶은 마음을 상징화했습니다.
소이캔들은 탈취 및 습도 조절을 도울 뿐만 아니라 거실, 방, 부엌, 화장실 등
다양한 공간에서 어두움을 밝혀주는 역할을 해요.
게다가 우리의 공간을 기분 좋은 향기로 가득 채워주기도 하죠.
저희는 이런 향기 가득한 소이 캔들에 특별한 메시지 문구를 주문제작 할 수 있는 ‘메시지 캔들’을 후원자 분들께 전하고 싶습니다.
메시지 소이캔들은 연말, 연초를 맞아 친구, 연인, 동료에게 선물하기 딱 좋은 리워드에요.
상대방에게 표현하지 못했던 고마운 마음을
조금 더 특별하고 색다르게 전할 수 있는 방법이죠.
그리고, 스스로인  "나"에게 하고 싶은 말을 전하기에도 좋답니다.
나만을 위한 메시지 문구가 드러나는 순간, 그 날 하루의 고단함은 따뜻한 촛불로 녹아 없어질 거에요.
가족에겐 미안하고 고마웠던 마음을, 친구에겐 응원의 메세지를,
연인에겐 더욱 더 특별한 감정을, 동료에겐 수고했다는 격려를
그리고 가장 수고했을 ‘내 자신’ 에게
‘메시지 향초’로 따뜻한 마음을 전해보는 건 어떨까요?
초를 녹이기 전에는 일반 향초에 불과하지만, 대략 1시간 이후가 지나면
 "상대방" 혹은 "나"에게 전하고자 하는 메시지가 서서히 보이기 시작합니다.
메시지를 기다리는 시간은 설렘과 기대로 가득할거에요.
무언가 비밀스러운 편지 같기도 하죠?
소이 메시지캔들을 밀어주시는 모든 분들에게
고급 선물포장 후 발송해 드립니다. (리본 색상은 검정색입니다.)
또한 제품 손상 방지를 위해서 완충제로 쌓여 배송됩니다.
향초는 메시지를 구분하기 위해서 포장박스에 위 사진처럼
메시지가 적힌 태그와 함께 발송됩니다.
받으신 리워드를 다른 분들에게 선물하실 땐 메시지 부분을 제거 후 선물하시면
전하고 싶으신 메시지를 더욱 특별하게 전달하실 수 있습니다.
찬 바람이 불어오는 겨울, 소중하고 특별한 지인들 또는 나에게 따뜻한 향기와 함께 메세지를 선물해보세요.
그리고 그 향기와 함께 따뜻한 손길로 유기견들을 보듬어 주세요.', '18000', '200', '400', '329', '0', '2021/08/01', '2021/10/01', '2021/11/05', '420', '1');

-----------------------------------------------------------------------------------

INSERT INTO kk_project VALUES ('3', 'classyart', '1', '유기견묘를 지켜주는 캔들, Light For Right', '버려진 강아지와 고양이들에게 안전한 삶을 만들어 주기 위한 펀딩프로젝트입니다.', 'Light for Right, 빛이 만드는 따뜻한 힘
빛은 그림자를 물리칩니다. 우리가 밝히는 빛이 세상의 모든 어둠을 없애진 못합니다. 하지만 그림자 속에 있는 생명에게 더 밝은 세상을 만들어 줄 수는 있습니다.
Light for Right은 제품을 판매한 금액으로 어려움에 처한 생명을 돕는 크라우드 펀딩 프로젝트입니다.
Light for Cat  Dog
스스로 버려지는 생명은 없습니다. 주인에게 버려졌지만 동물학대방지연합에게 구조되어 새로운 삶을 살고 있는 고양이와 강아지가 이번 Light for Right의 주인공입니다.
Light for Cat  Dog의 판매 수익금은 버려진 강아지와 고양이가 삶을 안전하게 보낼 수 있는 쉼터를 만들고 새로운 주인을 찾도록 하는 데 사용됩니다.
제품 소개
01 햇볕 아래 고양이의 나른한 오후 향 - 천연 소이 왁스
은은하면서 부드럽고 감미로운 향입니다. 신선한 배와 프리지아를 기본으로
파출리, 우디로 은은함을 더했습니다.
Small 90g (약 20시간 연소) | Medium 220g (약 45시간 연소)
02 산책을 준비하는 강아지의 마음 향 - 천연 소이 왁스
유쾌하고 활발한 느낌의 달콤한 향수입니다. 여린 봄 꽃, 과즙이 풍부한 복숭아, 아카시아 꿀 향기로 산책의 신선한 느낌을 담았습니다.
Small 90g (약 20시간 연소) | Medium 220g (약 45시간 연소)
캔들과 함께 세워 놓을 수 있는 리플렛과 스티커 3종을 함께 드립니다.
제품 추가 정보
Light for Cat  Dog는 천연 소이 왁스를 사용하며 위해우려 제품 자가검사 기준을
통과하였습니다. 요청 시 MSDS를 보내드립니다.
사용 시 주의사항
- 캔들 점화 시 심지를 3mm 정도로 잘라서 사용하면 그을음을 방지할 수 있습니다.
- 처음 사용시 가장자리까지 다 녹도록 연소시켜야 가운데만 녹는 터널 현상을 방지할 수 있습니다.
- 사용 시 1-2시간 사용이 효과적이나 3시간 이상은 태우지 마시고 환기를 시켜 주세요.
위해우려 제품 지정 및 안전?표시 기준에 따른 표시
품명: 일반 생활화학제품 (방향제) | 모델명: 소이캔들 | 모델 구분: 실내공기용, 향초형, 소이왁스 | 제조자명: 아이엠인터네셔널 | 제조국명: 대한민국 | 제조일시: 2018년 4월 |
성분: 소이왁스, 프레그런스 오일 | 용량: 90g (±5g), 220g (±10g)
자가검사번호: C-A09B-D043005-A150', '67000', '50', '300', '300', '0', '2021/07/01', '2021/09/15', '2021/10/15', '567', '1');

-----------------------------------------------------------------------------------

INSERT INTO kk_project VALUES ('4', 'freesia', '1', '재밌는 힐링타임! 문질러 녹이는 아이스크림 멜팅캔들 멜팅바', '어린 날의 소꿉놀이를 닮은 멜팅캔들로 지친 현대인에게 힐링시간과 소소한 행복을 선물합니다.', '소꿉멜츠
어린날의 소꿉놀이를 기억하시나요?
아주 평범한 놀이,
그렇지만 결코 평범하지만은 않은 추억 속의 그 놀이.
어쩌면 어린 내가 꿈꿨던 행복이 그려진 놀이.
그 시절, 행복을 꿈꾸던 어린 나처럼
나를 아끼는 마음을 담아 멜츠를 녹여보세요.
멜팅바 [Melting Bar]
소꿉멜츠 시리즈에서 새롭게 선보이는 멜팅바는 천연비즈왁스(밀랍)와 천연에센셜오일로 제작한 귀여운 아이스크림 모양인 멜팅 캔들로 원하는 만큼 문질러서 사용하는 것이 매력적인 캔들입니다.
워머에 문질 문질 문지르면 각각의 테마에 따라 블렌딩 된 아로마 향기로 아로마테라피를 즐길 수 있습니다.
# 아로마테라피
아로마테라피(aromatherapy)란 향기와 약효가 있는 식물(허브)에서 추출한 에센셜 오일(essential oil)을 주로 사용한 향기요법입니다. 아로마테라피는 스트레스를 완화를 통해 면역력 증진시키고 몸과 마음의 균형을 회복시키는 등 다양한 효과를 가지고 있다고 알려져 있습니다.
# 즐거운 힐링시간
기존의 아로마테라피에 놀이를 더하여 지친 일상에 재미가 필요한 이들에게 힐링시간을 선물하고자 하는 발상에서 기획하게 되었습니다.
포근한 휴식이 필요한 밤,
너무 많은 생각에 잠 못 들고 있다면
슬리핑바를 문질문질 문질러 보세요.
편안하고 달콤한 향이 마음을 다독여 줄 거예요.
호흡이 편안하지 않을 때
호흡이 답답할 때
상쾌한 숨을 위해 감기 비켜바를 문질 문질 문질러 보세요.
편안한 휴식을 느낄 수 있을 거예요.
울적하고 꿀꿀하고 외로운 시간
달콤하고 밝은 향으로 문질 문질 마음을 녹여보세요.
소중한 당신에게 따듯한 위로가 될 거예요.
마음이 초조하거나 아무것도 아닌 일에 자꾸만 화가 날 때,
이유 없이 짜증이 날 때,
자꾸만 남에게 신경을 부리게 될 때
이렇게 기분이 좋지 않은 날
빙그레 웃어바를 문질 문질 문질러보세요
행복이 가득한 향이 마음을 다듬어줄 거예요', '36000', '10', '50', '17', '0', '2020/05/25', '2020/06/20', '2020/07/31', '17', '1');

-----------------------------------------------------------------------------------

INSERT INTO kk_project VALUES ('5', 'freesia', '1', '보호종료아동의 시작을 응원할 당신의 온기, 캔들', '프리지아의 꽃말처럼, 캔들을 통해 "보호종료아동의 새로운 시작"을 응원하는 프로젝트입니다.', '#프로젝트의 목표
보호종료아동에 대해 들어본 적 있으신가요?
보호종료아동은 단어 그대로 보호가 ‘종료‘되어 보호시설을 떠나 세상 밖으로 나와야 하는 청소년들을 말합니다
아동복지법 제 16조 15항에는 ‘보호조치 중인 보호대상아동의 연령이 18세에 달하면, 보호조치를 종료하거나 해당 시설에서 퇴소시켜야 한다‘ 고 명시되어 있습니다. 즉, 보호종료아동은 만 18세가 되면 사회로 나와 홀로서기를 해야 하는 겁니다. 하지만 아직은 어린 18세, 보호종료아동이 맞닥뜨려야 하는 현실은 냉혹하기만 합니다.
그래서 저희는 보호종료아동을 기억하고 그들에 대한 관심을 제고하고자 이번 프로젝트를 시작하게 되었습니다. 보호종료아동을 향한 응원의 메세지가 담긴 프리지아 캔들을 사용하며, 사람들이 일상속에서 그들을 기억하고 응원하길 바랍니다.
# 리워드 제품 살펴보기

# 1. 길고 어두운 밤을 밝혀줄 [프리지아 캔들]
"보육원을 떠나던 첫날 만개한 프리지아 꽃다발을 받았어요. 아직도 시작을 앞둘 때면 그때 받았던 프리지아 향이 떠올라요"
-보호종료아동과의 인터뷰 中
보호종료아동의 기억이 담긴 프리지아 향 캔들 입니다. 가득 피어난 프리지아의 향기가 은은하게 일상 속에 녹아들어, 언제나 보호종료아동을 기억하길 바라는 마음을 담았습니다.
1) 9온즈 캔들
2) 5온즈 캔들
# 2. 따뜻한 향을 전해줄 [디퓨저 ? 룸스프레이]
다가오는 계절, 프리지아 향과 함께 더없이 잘 어울리는 화사한 봄꽃의 향입니다. 호불호 없는 향으로 선물용으로도 좋습니다.
1) 디퓨저
2) 룸스프레이', '14000', '50', '200', '97', '0', '2021/05/11', '2021/06/11', '2021/06/30', '155', '1');

-----------------------------------------------------------------------------------

INSERT INTO kk_project VALUES ('6', 'freesia', '1', '우리들의 시時와 공空의 향香', '소중한 순간時과 장소空,그때의 감정이 오롯이 담겨있는 향香을 통해 일상으로 불러오는 기억들', '어느덧 한해의 끝자락, 찬공기가 몸을 움츠러들게 만드는 계절이 왔습니다.
따스함이 필요한 우리들의 겨울을 위하여, 새로이 출시되는 [시공향 소이 캔들]로 인사드리게 되었습니다. 많은 사랑을 받았던 시공향의 5가지 향을 그대로 담아 낸, 시공향 소이 캔들을 기쁜마음으로 소개합니다.
과연 좋은 향이란 무엇일까요? 이글을 읽고 계신 여러분은 어떤 향을 좋아하시나요?
저희가 주변 사람들에게 어떤 향을 좋아하냐고, 어떤 향이 좋았냐고 물어봤을 때, 사람들은 정확히 어떤 향이라고 말하기 보다는 "향에 얽힌 장면을 묘사"하고 있다는 걸 알게 되었어요.
예를 들면,
"첫사랑의 머리카락에서 나던 샴푸 냄새라던가,
엄마가 엉덩이에 뿌려주셨던 베이비 파우더 냄새 같은 것들."
사실 첫사랑의 얼굴이나 어릴 때가 자세히 기억나진 않지만
비슷한 향을 맡을 때면 그때의 감정이 너무나 생생히 느껴진다면서요.
"그 때, 그 곳에서 느꼈던 감정들이 되살아나고
기억을 떠올리게 하는 그런 향.
소중한 순간(時), 장소(空),
그 때의 감정이 오롯이 담겨있는 향(香)."
리워드 안내
100% 식물성 콩왁스와 IFRA(국제 향료 협회)의 인증을 받은 프리미엄 등급의 향료를 배합하여 만드는 소이 캔들입니다. 타닥타닥 타는 나무심지가 주는 특유의 향과 소리를 함께 즐겨보세요. 가끔은 태우지 않고 코르크 리드만 열어서 향이 은은하게 퍼지도록 놔두어도 좋습니다.
시공향 소이캔들은 170g의 용량으로 25시간 이상의 연소시간을 가지고 있으며, 실내에서 사용하기에 적당한 사이즈입니다. 시공향의 5가지 향 중에 선택하실 수 있습니다.
미니성냥은 캔들 수량만큼 함께 담아드립니다.
예를들어, 캔들 3개 옵션 선택하시면 미니성냥도 동일하게 3개가 동봉됩니다.
성냥 1갑의 사이즈는 W55mm X H35mm X D15mm, 수량은 25~30개비 내외로 담기지만, 중량 기준이므로 오차가 있을 수 있습니다.
성냥 1갑은 시공향 캔들을 사용하는 동안 충분하게 사용할 수 있는 양입니다.
캔들 3개 이상 구매시부터는 시공향 패브릭 퍼퓸 정품 100ml(정가 14,000원)을 드립니다.
처음 텀블벅에서 공개되어 많은 응원에 힘입어 제품으로 출시되었으며, 지금은 맷드로우의 스테디 셀러로 자리잡은 섬유용 탈취 방향제입니다. 마찬가지로 시공향의 5가지 향 중에 선택하실 수 있습니다.
코르크 리드가 포함된 제품이며, 유리 컨테이너를 보호하기 위하여 한번 더 안전하게 완충처리를 한 뒤 박스 포장됩니다.
컨테이너 재질은 질감이 부드러운 무광의 반투명 유리이며, 내용은 전면에 인쇄(먹색)로 올라갑니다. 어두운 방안에서 캔들을 태우면 향의 이야기가 은은하게 빛날 수 있도록 문구를 넣었습니다.
박스는 무광코팅을 하여 오염에 강하고 내용물을 잘 보호할 수 있는 두께로 만들어집니다. 각 향별로 스토리가 핫 포일 스템핑으로 올라갑니다. (무광금색)
별도의 포장 없이 그대로 선물해도 좋을, 멀리서보면 차분하고 세심하게 살피면 자연스러움이 돋보이도록 만들었습니다.', '16000', '20', '100', '0', '0', '2021/08/31', '2021/09/30', '2021/11/01', '121', '1');

INSERT INTO kk_funding VALUES ('1', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('2', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('3', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('4', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('5', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('6', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('7', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('8', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('9', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);
INSERT INTO kk_funding VALUES ('10', 'id1', '1', '1', '39000', '김서은', '서울 성북구 서성동 1번지', '상세주소 1', '국민은행', '1111-1111-1111', '0', sysdate);

INSERT INTO kk_bookmark VALUES ('1', 'id1', '1');
INSERT INTO kk_bookmark VALUES ('2', 'id1', '2');
INSERT INTO kk_bookmark VALUES ('3', 'id1', '3');
INSERT INTO kk_bookmark VALUES ('4', 'id1', '4');
INSERT INTO kk_bookmark VALUES ('5', 'id1', '5');
INSERT INTO kk_bookmark VALUES ('6', 'id1', '6');
INSERT INTO kk_bookmark VALUES ('7', 'id1', '7');
INSERT INTO kk_bookmark VALUES ('8', 'id2', '1');
INSERT INTO kk_bookmark VALUES ('9', 'id2', '2');
INSERT INTO kk_bookmark VALUES ('10', 'id2', '3');
INSERT INTO kk_project VALUES ('30', 'id1', '1', '테스트', '테스트', '테스트', '50000', '1000', '100', '75', '75', '2021-08-20', '2021-10-19', '2021-11-03', '55', '1');

INSERT INTO kk_project VALUES ('31', 'id1', '1', '테스트2', '테스트2', '테스트2', '50000', '1000', '100', '50', '50', '2020-08-20', '2020-10-19', '2020-11-03', '55', '1');
INSERT INTO kk_project VALUES ('32', 'id1', '1', '테스트3', '테스트3', '테스트3', '50000', '1000', '100', '79', '79', '2021-08-20', '2021-10-19', '2021-11-03', '55', '1');
INSERT INTO kk_project VALUES ('33', 'id1', '1', '테스트4', '테스트4', '테스트4', '50000', '1000', '100', '100', '100', '2021-08-20', '2021-10-19', '2021-11-03', '55', '1');
INSERT INTO kk_project VALUES ('34', 'id1', '1', '테스트5', '테스트5', '테스트5', '50000', '1000', '100', '150', '150', '2021-08-20', '2021-10-19', '2021-11-03', '55', '1');

INSERT INTO kk_project VALUES ('35', 'id1', '1', '테스트3', '테스트3', '테스트3', '50000', '1000', '100', '79', '79', '2021-08-20', '2021-10-20', '2021-11-03', '55', '1');
INSERT INTO kk_project VALUES ('36', 'id1', '1', '테스트3', '테스트3', '테스트3', '50000', '1000', '100', '79', '79', '2020-08-20', '2020-10-20', '2021-11-03', '55', '1');
INSERT INTO kk_bookmark VALUES ('11', 'id1', '8');
INSERT INTO kk_bookmark VALUES ('12', 'id1', '9');
INSERT INTO kk_bookmark VALUES ('13', 'id1', '10');
INSERT INTO kk_bookmark VALUES ('14', 'id1', '11');
INSERT INTO kk_bookmark VALUES ('15', 'id1', '12');
INSERT INTO kk_bookmark VALUES ('16', 'id1', '13');
INSERT INTO kk_bookmark VALUES ('17', 'id1', '14');
INSERT INTO kk_bookmark VALUES ('18', 'id1', '15');
INSERT INTO kk_bookmark VALUES ('19', 'id1', '16');
INSERT INTO kk_bookmark VALUES ('20', 'id1', '17');
INSERT INTO kk_bookmark VALUES ('21', 'id1', '18');
INSERT INTO kk_bookmark VALUES ('22', 'id1', '19');
INSERT INTO kk_bookmark VALUES ('23', 'id1', '20');
INSERT INTO kk_bookmark VALUES ('24', 'id1', '21');
INSERT INTO kk_bookmark VALUES ('25', 'id1', '22');
INSERT INTO kk_bookmark VALUES ('26', 'id1', '23');
INSERT INTO kk_bookmark VALUES ('27', 'id1', '24');
INSERT INTO kk_bookmark VALUES ('28', 'id1', '25');
INSERT INTO kk_bookmark VALUES ('29', 'id1', '26');
INSERT INTO kk_bookmark VALUES ('30', 'id1', '27');


INSERT INTO kk_funding VALUES ('11', 'id1', '2', '1', '50000', '신경춘', '경기 김포시 경김동 11번지', '상세주소 11', '기업은행', '1111-1111-1121', '0', sysdate);
INSERT INTO kk_funding VALUES ('12', 'id2', '2', '1', '50000', '김강인', '강원 춘천시 강춘동 12번지', '상세주소 12', '기업은행', '1111-1111-1122', '0', sysdate);
INSERT INTO kk_funding VALUES ('13', 'id3', '2', '1', '50000', '이강보', '강원 인제군 강인동 13번지', '상세주소 13', '기업은행', '1111-1111-1123', '0', sysdate);
INSERT INTO kk_funding VALUES ('14', 'id4', '2', '1', '50000', '박충아', '충북 보은군 충보동 14번지', '상세주소 14', '기업은행', '1111-1111-1124', '0', sysdate);
INSERT INTO kk_funding VALUES ('15', 'id5', '2', '1', '50000', '최충전', '충남 아산시 충아동 15번지', '상세주소 15', '기업은행', '1111-1111-1125', '0', sysdate);
INSERT INTO kk_funding VALUES ('16', 'id6', '2', '1', '50000', '신전순', '전북 전주시 완산구 전전동 16번지', '상세주소 16', '하나은행', '1111-1111-1126', '0', sysdate);
INSERT INTO kk_funding VALUES ('17', 'id7', '2', '1', '50000', '신전강', '전북 순창군 전순동 17번지', '상세주소 17', '하나은행', '1111-1111-1127', '0', sysdate);
INSERT INTO kk_funding VALUES ('18', 'id8', '2', '1', '50000', '김전경', '전남 강진군 전강동 18번지', '상세주소 18', '하나은행', '1111-1111-1128', '0', sysdate);
INSERT INTO kk_funding VALUES ('19', 'id9', '2', '1', '50000', '이경고', '경북 경주시 경경동 19번지', '상세주소 19', '하나은행', '1111-1111-1129', '0', sysdate);
INSERT INTO kk_funding VALUES ('20', 'id10', '2', '1', '50000', '박경거', '경북 고령군 경고동 20번지', '상세주소 20', '하나은행', '1111-1111-1130', '0', sysdate);

commit;