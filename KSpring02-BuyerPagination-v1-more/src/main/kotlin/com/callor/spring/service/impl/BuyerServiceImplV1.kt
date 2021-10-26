    package com.callor.spring.service.impl

    import com.callor.spring.ConfigData
    import com.callor.spring.config.logger
    import com.callor.spring.model.Buyer
    import com.callor.spring.repository.BuyerRepository
    import com.callor.spring.service.BuyerService
    import org.springframework.beans.factory.annotation.Autowired
    import org.springframework.data.domain.Page
    import org.springframework.data.domain.PageRequest
    import org.springframework.data.domain.Pageable
    import org.springframework.stereotype.Service
    import java.util.*
    import kotlin.random.Random

    /**
     * 클래스의 매개변수를 사용하여 생성자 주입하기
     *
     * class 클래스명(주입받을객체,변수 선언)
     */
    @Service("bServiceV1")
    class BuyerServiceImplV1(val bRepo: BuyerRepository) : BuyerService {

        // setter 주입으로 와이어링 하기
        @Autowired
        lateinit var bDao: BuyerRepository

        // 현재 BuyerServiceImplV1 에서 사용할 가상 데이터를
        // 선언하기
        // private : 여기 클래스 내부에서만 사용하는 static 변수 선언하기

        override fun selectAll(): Array<Buyer> {
            // return ConfigData.BUYER_LIST
            return bRepo.findAll().toTypedArray()
        }

        override fun selectAll(pageable: Pageable): Page<Buyer> {
            return bRepo.findAll(pageable)
        }

        override fun selectWithPageable(intPage: Int): Array<Buyer> {
            // PageRequest.of(몇페이지, 몇개)
            // of : 내가 ()안에 값을 줄테니 pageRequest에게 객체를 만들어달라 요청하는 것
            val pageRequest = PageRequest.of(intPage, 10)
            return bRepo.findWithPagination(pageRequest).toTypedArray()
        }

        override fun findById(userid: String): Buyer {
    //        val findUser = ConfigData.BUYER_LIST.filter { buyer -> buyer.userid == userid }
    //        return findUser[0]

            // repository 의 findById() 는
            // 실제 데이터(Buyer)를 Optional 이라는 특별한 객체로
            // wrapping 하여 가져온다.
            // 필요한 데이터는 .get() method 를 사용하여
            // 한번 더 추출해 주어야 한다.
            val buyer:Optional<Buyer> = bRepo.findById(userid)
            return buyer.get()
        }

        // entity에 설정한 변수만 가져올 수 있다
        override fun findByName(name: String): Array<Buyer> {
    //        val userNum = ConfigData.RND.nextInt(ConfigData.BUYER_LIST.size)
    //        return arrayOf(ConfigData.BUYER_LIST[userNum])
            return bRepo.findByName(name)
        }

        override fun findByTel(tel: String): Array<Buyer> {
            return bRepo.findByTel(tel)
        }

        override fun insert(): Buyer {
            var userid = bRepo.maxUserId()
            /**
             * Repo 에서 return 받은 userid 에서 1번 위치(두 번재 문자열)부터 잘라서
             * 정수로 변환하여 userSeq 에 담아라
             * 만약 exception 이 발생하면 console 에 표시하고
             * 1 을 담아라
             */
            val userSeq = try {
                // repository로 받은 userid의 값이 있으면
                // B012 의 이니셜 B 를 잘라내고 012 추출
                userid?.substring(1)?.toInt() // 원래 Integer.valueOf를 썼었는데 toInt이 작동돼서 사용해봄
            } catch (e:Exception){
                logger().debug("고객데이터 없음")
                1 // 람다구조기때문에 return 쓰면 안된다. return 하면 함수 끝나는 부분으로 가버린다.
                // 그냥 써줘야 1 값이 userSeq 변수에 담긴다.
                // 즉, 고객이 아무도 없으면 첫 고객으로 B001 이 생성되는 것이다.
            }
            if(userSeq != null) {
                userid = String.format("B%03d", userSeq + 1)
            }
            return Buyer(userid = userid)
        }

        override fun insert(buyer: Buyer): Buyer {
            // Insert Or Update
            // 기존데이터의 id와 다른 값이면 insert
            // 기존 데이터와 같은 id가 들어오면 update
            return bDao.save(buyer)
        }

        override fun delete(userid: String) {
            bDao.deleteById(userid)
        }

        override fun update(buyer: Buyer): Buyer {
            // 혹시모를 나중을 대비해서 insert와 update를 분리해 놓은것이지 기능은 같다
            return bDao.save(buyer)
        }


    }