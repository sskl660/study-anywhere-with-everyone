<template>
    <div>
        <div>
            <!-- style="display:block;position:absolute;top:0px;left:0px;height:100%;width:100%;background:transparent;overflow:hidden;visibility:hidden;" -->

            <!-- <svg id="svg" xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" style="display:block;position:absolute;top:0px;left:0px;visibility:hidden;z-index:1000;">
                <line id="vec" x1="0" y1="0" x2="0" y2="0" stroke="#ff0000" stroke-width="1.5" stroke-dasharray="3,3">
                </line>
            </svg> -->
            <!-- <div id="gravitybtn" style="display: block;position: fixed;margin: 4px;top:0;right:0;border-radius:5px;height: 30px;width: 130px;padding: 5px; background-color:#fff;text-align: center;font: italic bold 16px verdana;color: #fff;line-height: 30px;letter-spacing: 0.5px;cursor:default;z-index: 1000;-moz-user-select: none;user-select: none;-webkit-user-select: none;-ms-user-select: none;visibility:visible;"> -->
            <div id="gravitybtn">
                Gravity
                <input type="radio" name="c" id="but1" checked="" />
                <input type="radio" style="cursor:pointer;" name="c" id="but2" />
            </div>

            <!-- <div v-for="(idx, ssazip_num) in temp_galaxy_data" :key="ssazip_num">
                <SmallSSazip />
                </div> -->
            <div id="oldPlace">
                <!-- <div v-for="(sazibi, index) in participantsVuex" :key="index"> -->
                <!-- <SmallSSazip :sazibi="sazibi" /> -->
                <!-- <div :id="'ssazip' + sazibi.partEmail">
                        <img src="/img/ssazip.43ffb363.png" style="width:50px" />
                        <span style="color:#fff">{{ sazibi }}</span>
                    </div>
                </div> -->
            </div>

            <!-- <div v-for="(idx,index) in participantsVuex" :key="index"> -->
            <!-- {{index}} -->

            <!-- <div v-for="(idx, ssazip_num) in participantsVuex" :key="ssazip_num"> -->
            <!-- <SmallSSazip /> -->
            <!-- <SmallSSazip @click="tmpClicker(zibi)" :idx="ssazip_num"/> -->
            <!-- <SmallSSazip @click="tmpClicker(zibi)" :idx="zibi"/> -->
            <!-- </div> -->
            <!-- <img id="ssazip" src="@/assets/ssazip.png" style="width:50px" >   -->
            <!-- <div id="ssazip">
                <img  src="@/assets/ssazip.png" style="width:80px; height:80px" >
                <span style="color:#fff;"><i class="fas fa-star"></i>나<i class="fas fa-star"></i></span>
            </div> -->
            <!-- <div style="background-color:white" v-for="(zibi) in participantsVuex" :key=" zibi">
                {{zibi}}
                    <img id="ssazip" src="@/assets/ssazip.png" style="width:50px" >
            </div> -->
            <div style="color:red" v-for="(user, index) in participantsVuex" :key="index"></div>
        </div>
        <!-- <img id="ssazip" src="@/assets/ssazip.png" style="width:50px" /> -->
        <!-- 
        <div v-for="user in participants" :key="user">{{ user }}123</div>
        <div v-for="user in participantsVuex" :key="user">{{ user }}  123</div> -->
    </div>
</template>

<script>
import SmallSSazip from '@/components/galaxy/SmallSSazip.vue';
// import gravity from "@/components/temporary/gravity.js"
// import $ from 'jquery';
import { mapGetters } from 'vuex';

export default {
    name: 'SSazip',
    clocker: null,
    components: {
        SmallSSazip,
    },
    giveme: null,
    ccc: null,
    data() {
        return {
            // 소켓 연결
            stompClient: null,
            ani: '',
            participants: [], // length. 참여자수, 이메일 userEmail
            beforeParts: [],
            beforeBalls: [],
            beforeBallCoords: [],
            // 참여자 정보 전달 채널
            partChannel: null,
            // 상위 5명
            ranker: [],
            temp_galaxy_data: [0, 1, 2, 4, 12, 6],
        };
    },
    created: function() {
        // this.gravity();
        // this.participants=this.participantsVuex;
    },
    beforeRouteLeave(to, from, next) {
        clearInterval(this.clocker);

        next();
        // }
    },
    mounted: function() {
        // while(document.getElementById('ssazip'))document.getElementById('ssazip').remove();
        // this.gravity();
        // this.participants=this.participantsVuex;
        // this.socketConnect();
        //         var cell = document.getElementById("asd");
        //    while ( cell.hasChildNodes() ) { console.log("!!!!!@!#!@#!@#@!!#####")
        //        cell.removeChild( cell.firstChild ); }
    },
    beforeUpdate: function() {
        console.log('bf');
        console.log(document.getElementById('newDivSpace'));
    },
    updated: function() {
        this.gravity();
        console.log('updated');
        this.clocker = setInterval(this.showRemaining, 1000);
        // cancelAnimationFrame(this.ani);
        //document.getElementById('newDivSpace').remove();
        // this.participants=this.participantsVuex;
        // this.gravity();
    },
    computed: {
        ...mapGetters(['participantsVuex']),
    },
    methods: {
        profileF(senderId) {
            let routeData = this.$router.resolve({ name: 'Profile', query: { user: senderId } });
            window.open(routeData.href, '_blank');
        },
        async showRemaining() {
            let now = new Date();
            for (var i = 0; i < this.participantsVuex.length; i++) {
                var date = this.participantsVuex[i].enterTime;
                var vDate = new Date(date); // 전달 받은 일자
                vDate -= new Date().setTime(9);
                var second = 1000;
                var minute = second * 60;
                var hour = minute * 60;
                var day = hour * 24;

                let distDt = now - vDate - 1;
                // var days = Math.floor(distDt /day);
                // var hours = Math.floor((distDt % day) / hour);
                var hours = Math.floor(distDt / hour);
                var minutes = Math.floor((distDt % hour) / minute);
                var seconds = Math.floor((distDt % minute) / second);
                // document.getElementById(this.participantsVuex[i].partEmail).textContent = days + '일 ';
                document.getElementById(this.participantsVuex[i].partEmail).textContent = hours + '시간';
                document.getElementById(this.participantsVuex[i].partEmail).textContent += minutes + '분';
                document.getElementById(this.participantsVuex[i].partEmail).textContent += seconds + '초 공부 중';
            }
        },
        tmpClicker(param) {
            alert(param);
        },

        gravity: function() {
            console.log('gravity');
            console.log(this.participantsVuex);
            // console.log(document.getElementById('newDivSpace'));
            // while(document.getElementById('ssazip'))
            //  document.getElementById('newDivSpace').remove();
            /*
        Play Balls 2 (full page version).
        Straight JavaScript!
        kurt.grigg@yahoo.co.uk
        */

            var partList = this.participantsVuex;
            var beforeList = this.beforeParts;
            var numberOfBalls = 50;
            var ballVelocity = 2; //2
            var ballHardness = 0.85;
            var wallHardness = 0.5;
            var floorHardness = 0.6;
            var minRadius = 35;
            var maxRadius = 35;
            var ballColour = '#dd0000'; //Leave blank for random colours.
            var milliSeconds = 30;
            var gameGravity = 1.0;
            var vectorLineColour = '#ff0000';

            var then = performance.now();
            var h,
                w,
                balls = this.beforeBalls,
                ballCoords = this.beforeBallCoords;
            var cony = 0;
            var conx = 0;
            var d = document;
            var force = 0.05;
            var exempt = false;
            var newBall = false;
            var drop = false;
            var m = { down: false, x1: 0, y1: 0, x2: 0, y2: 0 };

            document.oncontextmenu = function() {
                return false;
            };

            // window.addEventListener('mousedown', function(e) {
            //     if (!e) {e = window.event;}
            //     m.down = true;
            //     m.x1 = m.x2 = e.pageX - scrl(1);
            //     m.y1 = m.y2 = e.pageY - scrl(0);

            //     d.body.style.cursor = 'crosshair';
            //     d.getElementById('svg').style.visibility = 'visible';
            //     d.getElementById('vec').setAttribute('x1', m.x1);
            //     d.getElementById('vec').setAttribute('y1', m.y1);
            // }, false);

            // window.addEventListener("mousemove", function(e) {
            //     if (!e) {e = window.event;}
            //     if (m.down) {
            //         m.x2 = e.pageX - scrl(1);
            //         m.y2 = e.pageY - scrl(0);
            //     }
            //     else {
            //         m.x1 = m.x2 = e.pageX - scrl(1);
            //         m.y1 = m.y2 = e.pageY - scrl(0);
            //     }
            //     d.getElementById('vec').setAttribute('x2', m.x2);
            //     d.getElementById('vec').setAttribute('y2', m.y2);
            // }, false);

            // window.addEventListener("mouseup", function(e) {
            //     if (!e) {e = window.event;}
            //     if (m.down) {
            //         m.down = false;
            //         d.body.style.cursor = 'default';
            //         d.getElementById('svg').style.visibility = 'hidden';

            //         var dx = (m.x1 - m.x2);
            //         var dy = (m.y1 - m.y2);
            //         var dist =  Math.sqrt(dx * dx + dy * dy);

            //         var f = (drop)?1:0.3;

            //         var nvx = (dx / dist * -(dist * force)) * f;
            //         var nvy = (dy / dist * -(dist * force)) * f;

            //         // 드래그해서 공 생성
            //         // if (!exempt && dist > 0) {
            //         //     createBall(m.y1,m.x1,nvy,nvx);
            //         // }
            //     }
            // }, false);

            function scrl(a) {
                var y, x;
                y = window.pageYOffset;
                x = window.pageXOffset;
                return a == 0 ? y : x;
            }

            function iniscrl() {
                con.style.top = cony + scrl(0) + 'px';
                con.style.left = conx + scrl(1) + 'px';
            }

            function gravOn() {
                drop = true;
                gravCon.style.backgroundColor = '#00aa00';
            }

            function gravOff() {
                drop = false;
                gravCon.style.backgroundColor = '#ee0000';
            }

            function randomColour() {
                var rgb = [];
                for (var i = 0; i < 3; i++) {
                    var gen = (50 + Math.random() * 205) | 0;
                    rgb.push(gen);
                }
                return typeof ballColour == 'undefined' || ballColour == '' ? 'rgb(' + rgb.toString() + ')' : ballColour;
            }

            function win() {
                var ddw = d.documentElement.clientWidth;
                var ddh = d.documentElement.clientHeight;
                var scrollBarRight = typeof ddw == 'number' ? window.innerWidth - ddw : 0;
                var scrollBarBottom = typeof ddh == 'number' ? window.innerHeight - ddh : 0;
                // 채팅방 가리지 않게 만들기
                h = window.innerHeight - scrollBarBottom - 1;
                w = window.innerWidth - scrollBarRight - 500;
                // h = window.innerHeight - scrollBarBottom - 1;
                // w = window.innerWidth - scrollBarRight - 1;
            }

            // ///기존의 새로  생성하는 new div space code
            // if (!document.getElementById('newDivSpace')) {
            //     //처음이라면
            //     console.log('=================make newDivSpace=========================================');
            //     var con = d.createElement('div');
            //     con.setAttribute('id', 'newDivSpace');
            //     con.setAttribute(
            //         'style',
            //         'display:block;' +
            //             'position:absolute;' +
            //             'top:0px;left:0px;' +
            //             'height:100%;width:100%;' +
            //             'background:transparent;' +
            //             'overflow:hidden;' +
            //             'visibility:hidden;'
            //     );

            //     con.innerHTML +=
            //         '<svg id="svg" xmlns="http://www.w3.org/2000/svg" width="100%" height="100%"' +
            //         ' style="display:block;position:absolute;top:0px;left:0px;visibility:hidden;z-index:1000;">' +
            //         '<line id="vec" x1="0" y1="0" x2="0" y2="0" stroke="' +
            //         vectorLineColour +
            //         '" stroke-width="1.5" stroke-dasharray="3,3">' +
            //         '</svg>';

            //     d.body.appendChild(con);
            // } else {
            //     //처음아니라면
            //     var con = document.getElementById('newDivSpace');
            //     console.log('=============================stay DivSpace==========================');
            //     console.log(con);
            //     // con.removeChild(d.getElementById('ssazip'));
            // }

            ///try new

            console.log('=================use oldDivSpace=========================================');
            // console.log('=================make newDivSpace=========================================');
            // var con = d.createElement('div');
            var con = document.getElementById('oldPlace');

            con.setAttribute(
                'style',
                'display:block;' +
                    'position:absolute;' +
                    'top:0px;left:0px;' +
                    'height:100%;width:100%;' +
                    'background:transparent;' +
                    'overflow:hidden;' +
                    'visibility:hidden;'
            );

            con.innerHTML +=
                '<svg id="svg" xmlns="http://www.w3.org/2000/svg" width="100%" height="100%"' +
                ' style="display:block;position:absolute;top:0px;left:0px;visibility:hidden;z-index:1000;">' +
                '<line id="vec" x1="0" y1="0" x2="0" y2="0" stroke="' +
                vectorLineColour +
                '" stroke-width="1.5" stroke-dasharray="3,3">' +
                '</svg>';

            function xy(a, s) {
                return (a * s) / 100;
            }
            // console.log('만들공간인 oldDiv의 상태입니다');
            // console.log(con);
            /////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////
            function createBall(y, x, idx, newOneIdx, nvy, nvx) {
                // var r = (minRadius + Math.random() * maxRadius) | 0;
                // r: 알들의 크기
                var r = 50;
                // var newball=document.createElement('img');
                // newball.setAttribute('id',this.)
                console.log('idx in partList');
                console.log(idx);
                if (newOneIdx != -1) {
                    idx = newOneIdx;
                    console.log('추가인원생성 예정');
                }

                console.log('------------create Ball--------------');
                console.log('con 이란 생성전 싸집이공간');
                console.log(JSON.parse(JSON.stringify(con.childElementCount)));

                console.log('참여자 목록');
                console.log(JSON.parse(JSON.stringify(partList)));
                console.log('생성할 참여자');
                console.log(partList[idx].partEmail);
                //이메일을 가지는 엘리먼트로 속성 지정
                //////////oldDiv
                // console.log('idx==newIdx')
                // console.log(idx==newIdx)
                // if (deathIdx != -1 && d.getElementById(`ssazip${beforeList[idx]}`) != null) {
                //     console.log(partList[idx].partEmail);
                //     console.log('삭제');
                //     var ball = d.getElementById(`ssazip${beforeList[idx]}`);
                //     ball.parentNode.removeChild(ball);
                //     balls.splice(idx, 1);
                //     ballCoords.splice(idx, 1);
                //     return;
                // } else

                if (d.getElementById(`ssazip${partList[idx].partEmail}`) != null) {
                    console.log(partList[idx].partEmail);
                    console.log('는 있던 친구네!');
                    var ball = d.getElementById(`ssazip${partList[idx].partEmail}`);
                }
                // var ball = d.getElementById(`ssazip${partList[idx].partEmail}`);
                else {
                    var ball = d.createElement('div');
                    console.log(partList[idx].partEmail);
                    console.log('는 새로 생성 할 친구네!');
                    const num = parseInt(Math.random() * 5);
                    var ssazip_pics = [
                        '/img/ssazip.43ffb363.png',
                        '/img/ssazip_mint.3c829d99.png',
                        '/img/ssazip_orange.6b3c93af.png',
                        '/img/ssazip_purple.fe37cc0a.png',
                        '/img/ssazip_red.d0a2d170.png',
                        '/img/ssazip_yellow.137c1665.png',
                    ];
                    // var nowDate = new Date();
                    // var count = nowDate - partList[idx].enterTime;
                    ball.setAttribute('id', `ssazip${partList[idx].partEmail}`);
                    //@click="profileF(`+partList[idx].partEmail+`)"
                    ball.innerHTML +=
                        `<img src="` +
                        ssazip_pics[num] +
                        `" style="width:50px" />
                            <span class="" style=" color:#fff; width:150px"> ${partList[idx].partName} <br/>
<div style="width:160px" id="` +
                        partList[idx].partEmail +
                        `"></div>  </span>
                            `;

                    // var cell = document.getElementById('oldPlace');

                    // if ( idx==partList.length-1) {
                    //     console.log("삭제한다요")
                    //     var cell = document.getElementById("oldPlace");
                    // while ( cell.hasChildNodes() ) { cell.removeChild( cell.firstChild ); }
                    //     }
                    // if (ball == null) {
                    //     console.log('새로운 컨포넌트 생성!!ㅣㅏㅁ누이ㅏㄴㅁ우');
                    //     console.log(partList[idx]);
                    //     // ball=d.createElement("SmallSSazip",{type: rops:{ssazibi:partList[idx]}})
                    // }

                    ball.setAttribute(
                        'style',
                        'display:block;' +
                            // +'src: url(~@/assets/sszip.png);'
                            'position:absolute;' +
                            'height:' +
                            r +
                            'px;' +
                            'width:' +
                            r +
                            'px;' +
                            'top:0px;left:0px;' +
                            // +'background-color:'+randomColour()+';'
                            'border-radius:50%;' +
                            'box-shadow:inset 0 0 ' +
                            xy(1, r) +
                            'px ' +
                            xy(1, r) +
                            'px rgba(0,0,0,0.2),' +
                            'inset 0 -' +
                            xy(25, r) +
                            'px ' +
                            xy(50, r) +
                            'px rgba(0,0,0,0.4);' +
                            'opacity:1.0;'
                    );
                }
                console.log('xy');
                console.log(xy);
                // }

                setTimeout(function() {
                    ball.style.visibility = 'visible';
                }, 50);

                // console.log('con');
                // console.log(con);

                // console.log('con.childNodes');
                // console.log(con.childNodes);
                // console.log('con.children');
                // console.log(con.children);
                // // console.log('포함여부를 위한 콘솔');
                // // console.log(document.getElementById(`div#ssazip${partList[idx].partEmail}`).contains(con.childNodes));
                // // if (!con.childNodes.contains(`ssazip${partList[idx].partEmail}`)) {
                // // console.log(con.hasChildNodes(`div#ssazip${partList[idx].partEmail}`));
                // console.log('con.children.namedItem(`ssazip${partList[idx].partEmail}`)');
                // console.log(con.children.namedItem(`ssazip${partList[idx].partEmail}`));
                // console.log('con.children.namedItem("gravitybtn")');
                // console.log(con.children.namedItem('gravitybtn'));
                // if (document.getElementById(`div#ssazip${partList[idx].partEmail}`).contains(con.children)) {
                // if (con.children.namedItem(`ssazip${partList[idx].partEmail}`) != null) {
                //     // console.log('있던거네 일단 삭제함요');
                //     console.log('있던거네 일단 패스함요');
                //     console.log(con.children.namedItem(`ssazip${partList[idx].partEmail}`));
                //     // con.removeChild(con.children.namedItem(`ssazip${partList[idx].partEmail}`));
                //     // con.removeChild(ball);
                // }
                // //
                // else {
                if (!document.getElementById(`ssazip${partList[idx].partEmail}`)) {
                    //&&!document.getElementById(`ssazip${partList[idx].partEmail}`).contains(con)) {
                    console.log('공이없다 추가한다링');
                    console.log(JSON.parse(JSON.stringify(ballCoords)));

                    con.appendChild(ball);
                    console.log(nvy);
                    ballAttr(r, y, x, nvy, nvx, idx);
                    balls.splice(idx, 0, ball);
                    console.log('con 이란 생성 후 싸집이공간');
                    //                     console.log(JSON.parse(JSON.stringify(balls[0])));
                    // console.log(JSON.parse(JSON.stringify(ballCoords[0])));
                    // console.log(JSON.parse(JSON.stringify(con.childNodes[0])));
                } else {
                    console.log('있던거네');
                    balls.splice(idx, 1, ball);
                    // console.log(JSON.parse(JSON.stringify(balls[0])));
                    // console.log(JSON.parse(JSON.stringify(ballCoords[0])));
                }
                for (var i = 0; i < balls.length; i++) {
                    console.log(JSON.parse(JSON.stringify(balls[i].id)));
                }
            }

            // 중력 무중력 조작기
            // var gravCon = d.createElement('div');
            var gravCon = d.getElementById('gravitybtn');
            gravCon.setAttribute(
                'style',
                'display: block;' +
                    'position: fixed;' +
                    'margin: 4px;' +
                    'top:35px; left:450px;' +
                    'border-radius:5px;' +
                    'height: 45px;' +
                    'width: 130px;' +
                    'padding: 5px;' +
                    'background-color:#F1C069;' +
                    'text-align: center;' +
                    'font: italic bold 16px verdana;' +
                    'color: #1C84C4;' +
                    'line-height: 35px;' +
                    'letter-spacing: 0.5px;' +
                    'cursor:default;' +
                    'z-index: 1000;' +
                    '-moz-user-select: none;user-select: none;-webkit-user-select: none;-ms-user-select: none;' +
                    'visibility:visible;'
            );

            gravCon.innerHTML =
                '중력 ON!!! <input type="radio" style="cursor:pointer;" name="c" id="but1" checked>' +
                '<input type="radio" style="cursor:pointer;"name="c" id="but2">';

            con.appendChild(gravCon);

            function ballAttr(rad, y, x, nvy, nvx, idx) {
                console.log('ballAttr');

                // if (balls.length < numberOfBalls) {
                newBall = false;
                // }

                var zx = newBall ? x - nvx : 0;
                var zy = newBall ? y - nvy : 0;

                var vex = newBall ? nvx : -ballVelocity + Math.random() * (ballVelocity * 2);
                var vey = newBall ? nvy : -ballVelocity + Math.random() * (ballVelocity * 2);

                var b = {
                    r: rad * 0.5,
                    m: rad * 0.5,
                    x: x,
                    y: y,
                    px: zx,
                    py: zy,
                    ax: 0,
                    ay: 0,
                    vx: vex,
                    vy: vey,
                };
                console.log('ballCoords');
                console.log(ballCoords);

                // ballCoords.push(b);
                ballCoords.splice(idx, 0, b);
                console.log(ballCoords);
            }

            function animate() {
                if (drop) {
                    return false;
                } else {
                    for (var i = 0; i < balls.length; i++) {
                        var b = ballCoords[i];
                        b.x += b.vx;
                        b.y += b.vy;

                        b.px = b.x - (50 * b.vx) / 100;
                        b.py = b.y - (50 * b.vy) / 100;
                    }
                }
            }

            function zeroGravityBorderCollisions() {
                for (var i = 0; i < balls.length; i++) {
                    const b = ballCoords[i];
                    if (b.x > w - b.r) {
                        b.x = w - b.r;
                        b.vx *= -1;
                    } else if (b.x < b.r) {
                        b.x = b.r;
                        b.vx *= -1;
                    }

                    if (b.y > h - b.r) {
                        b.y = h - b.r;
                        b.vy *= -1;
                    } else if (b.y < b.r) {
                        b.y = b.r;
                        b.vy *= -1;
                    }
                }
            }

            function gravityBorderCollisions() {
                for (var i = 0; i < balls.length; i++) {
                    var b = ballCoords[i];

                    var x = b.x;
                    var y = b.y;

                    if (x - b.r < 0) {
                        var vx = (b.px - b.x) * wallHardness;
                        b.x = b.r;
                        b.px = b.x - vx;
                    } else if (x + b.r > w) {
                        var vx = (b.px - b.x) * wallHardness;
                        b.x = w - b.r;
                        b.px = b.x - vx;
                    }
                    if (y - b.r < 0) {
                        var vy = (b.py - b.y) * wallHardness;
                        b.y = b.r;
                        b.py = b.y - vy;
                    } else if (y + b.r > h) {
                        var vy = (b.py - b.y) * floorHardness;
                        b.y = h - b.r;
                        b.py = b.y - vy;
                    }
                }
            }

            function collisions(preserve) {
                for (var i = 0; i < balls.length; i++) {
                    var b1 = ballCoords[i];
                    for (var j = i + 1; j < balls.length; j++) {
                        var b2 = ballCoords[j];

                        var dx = b1.x - b2.x;
                        var dy = b1.y - b2.y;
                        var len = dx * dx + dy * dy;
                        var min = b1.r + b2.r;
                        var mind = min * min;
                        var ca = Math.atan2(dy, dx);

                        if (len < mind) {
                            var dist = Math.sqrt(len);

                            b1.tx = b1.x - b1.px;
                            b1.ty = b1.y - b1.py;
                            b2.tx = b2.x - b2.px;
                            b2.ty = b2.y - b2.py;

                            var factor = (dist - min) / dist;
                            b1.x -= dx * factor * 0.5;
                            b1.y -= dy * factor * 0.5;
                            b2.x += dx * factor * 0.5;
                            b2.y += dy * factor * 0.5;

                            if (preserve) {
                                if (drop) {
                                    var v1 = Math.sqrt(b1.tx * b1.tx + b1.ty * b1.ty);
                                    var v2 = Math.sqrt(b2.tx * b2.tx + b2.ty * b2.ty);

                                    var a1 = Math.atan2(b1.ty, b1.tx);
                                    var a2 = Math.atan2(b2.ty, b2.tx);

                                    var rvx1 = v1 * Math.cos(a1 - ca);
                                    var rvy1 = v1 * Math.sin(a1 - ca);
                                    var rvx2 = v2 * Math.cos(a2 - ca);
                                    var rvy2 = v2 * Math.sin(a2 - ca);

                                    var evx1 = ((b1.m - b2.m * ballHardness) * rvx1 + (b2.m + b2.m * ballHardness) * rvx2) / (b1.m + b2.m);
                                    var evx2 = ((b1.m + b1.m * ballHardness) * rvx1 + (b2.m - b1.m * ballHardness) * rvx2) / (b1.m + b2.m);
                                    var evy1 = rvy1;
                                    var evy2 = rvy2;

                                    b1.tx = Math.cos(ca) * evx1 + Math.cos(ca + Math.PI / 2) * evy1;
                                    b1.ty = Math.sin(ca) * evx1 + Math.sin(ca + Math.PI / 2) * evy1;
                                    b2.tx = Math.cos(ca) * evx2 + Math.cos(ca + Math.PI / 2) * evy2;
                                    b2.ty = Math.sin(ca) * evx2 + Math.sin(ca + Math.PI / 2) * evy2;
                                } else {
                                    var v1 = Math.sqrt(b1.vx * b1.vx + b1.vy * b1.vy);
                                    var v2 = Math.sqrt(b2.vx * b2.vx + b2.vy * b2.vy);

                                    var a1 = Math.atan2(b1.vy, b1.vx);
                                    var a2 = Math.atan2(b2.vy, b2.vx);

                                    var rvx1 = v1 * Math.cos(a1 - ca);
                                    var rvy1 = v1 * Math.sin(a1 - ca);
                                    var rvx2 = v2 * Math.cos(a2 - ca);
                                    var rvy2 = v2 * Math.sin(a2 - ca);

                                    var evx1 = ((b1.m - b2.m) * rvx1 + (b2.m + b2.m) * rvx2) / (b1.m + b2.m);
                                    var evx2 = ((b1.m + b1.m) * rvx1 + (b2.m - b1.m) * rvx2) / (b1.m + b2.m);
                                    var evy1 = rvy1;
                                    var evy2 = rvy2;

                                    b1.vx = Math.cos(ca) * evx1 + Math.cos(ca + Math.PI / 2) * evy1;
                                    b1.vy = Math.sin(ca) * evx1 + Math.sin(ca + Math.PI / 2) * evy1;
                                    b2.vx = Math.cos(ca) * evx2 + Math.cos(ca + Math.PI / 2) * evy2;
                                    b2.vy = Math.sin(ca) * evx2 + Math.sin(ca + Math.PI / 2) * evy2;
                                }
                                b1.px = b1.x - b1.tx;
                                b1.py = b1.y - b1.ty;
                                b2.px = b2.x - b2.tx;
                                b2.py = b2.y - b2.ty;
                            }
                        }
                    }
                }
            }

            function gravityF() {
                for (var i = 0; i < balls.length; i++) {
                    var b = ballCoords[i];
                    b.ay += gameGravity;
                }
            }

            function inertia(delta) {
                for (var i = 0; i < balls.length; i++) {
                    var b = ballCoords[i];

                    var x = b.x * 2 - b.px;
                    var y = b.y * 2 - b.py;

                    b.px = b.x;
                    b.py = b.y;

                    b.x = x;
                    b.y = y;
                }
            }

            function accelerate(delta) {
                for (var i = 0; i < balls.length; i++) {
                    var b = ballCoords[i];

                    b.x += b.ax * delta * delta;
                    b.y += b.ay * delta * delta;

                    b.ax = 0;
                    b.ay = 0;
                }
            }
            function faccelerate(delta) {
                console.log('faccelerate');
                for (var i = 0; i < balls.length; i++) {
                    var b = ballCoords[i];

                    b.x += b.ax * delta * delta;
                    b.y += b.ay * delta * delta;
                    console.log('-------------------aaaaccccc-------------');

                    b.ax = 0;
                    b.ay = 0;
                }
            }

            function assignToHTML() {
                for (var i = 0; i < balls.length; i++) {
                    var b = ballCoords[i];
                    balls[i].style.transform = 'translate3d(' + (b.x - b.r) + 'px, ' + (b.y - b.r) + 'px, 0) rotate(22.5deg)';
                }
            }

            function restart() {
                for (var i = 0; i < balls.length; i++) {
                    var b = ballCoords[i];
                    b.vx = b.x - b.px;
                    b.vy = b.y - b.py;
                }
            }

            function step() {
                var steps = 2;
                var delta = 1 / steps;
                for (var i = 0; i < steps; i++) {
                    if (drop) {
                        //Thanks to Florian Boesch for this anti-jitter doubling trick.
                        gravityF();
                        accelerate(delta);
                        collisions(false);
                        zeroGravityBorderCollisions();
                        inertia(delta);
                        collisions(true);
                        gravityBorderCollisions();
                    }
                }
            }
            function fstep() {
                console.log('fstep');

                var steps = 2;
                var delta = 1 / steps;
                for (var i = 0; i < steps; i++) {
                    if (drop) {
                        //Thanks to Florian Boesch for this anti-jitter doubling trick.
                        gravity();
                        faccelerate(delta);
                        collisions(false);
                        zeroGravityBorderCollisions();
                        inertia(delta);
                        collisions(true);
                        gravityBorderCollisions();
                    }
                }
            }

            function run() {
                var now = performance.now();
                if (now - then > milliSeconds) {
                    for (var i = 0; i < ballCoords.length; i++) {
                        if (ballCoords[i].vx > 0.6) {
                            ballCoords[i].vx = 0.5;
                        }
                        if (ballCoords[i].vy > 0.6) {
                            ballCoords[i].vy = 0.5;
                        }
                        if (ballCoords[i].vx < -0.6) {
                            ballCoords[i].vx = -0.5;
                        }
                        if (ballCoords[i].vy < -0.6) {
                            ballCoords[i].vy < -0.5;
                        }
                    }
                    animate();
                    if (!drop) {
                        collisions(true);
                        zeroGravityBorderCollisions();
                    }
                    step();
                    assignToHTML();
                    then = performance.now();
                }
                window.requestAnimationFrame(run);
            }
            function firstrun() {
                console.log('first');
                console.log(balls);
                console.log(ballCoords);

                var now = performance.now();
                if (now - then > milliSeconds) {
                    animate();
                    if (!drop) {
                        collisions(true);
                        zeroGravityBorderCollisions();
                    }
                    fstep();
                    assignToHTML();
                    then = performance.now();
                }
                window.requestAnimationFrame(run);
            }

            //=========================================================================
            //=========================================================================
            //=========================================================================
            //=========================================================================
            //=========================================================================
            //=========================================================================
            //=========================================================================
            //=========================================================================
            function start() {
                win();

                console.log('---------------------startFuntion------------');
                console.log('partList  신 참여자 리스트');
                console.log(JSON.parse(JSON.stringify(partList)));
                console.log('partList.length 신 참여자 수');
                console.log(partList.length);
                console.log('beforeList 이전 참여자 리스트(이메일)');
                console.log(JSON.parse(JSON.stringify(beforeList)));
                console.log('beforeList.leng 이전참여자 수');
                console.log(beforeList.length);
                console.log('balls.length 기존 볼, 갯수');
                console.log(JSON.parse(JSON.stringify(balls)));
                console.log(balls.length);
                console.log('con.childNodes 싸집이 공간');
                console.log(JSON.parse(JSON.stringify(con.childNodes)));
                console.log('con.childNodes.length-2 싸집이 갯수???');
                console.log(con.childNodes.length - 2);
                console.log('=========================================');

                // var num = con.childNodes.length - 2;
                // if (num == 0) num = partList.length;
                var num = partList.length;

                ///old
                var newOneIdx = -1;
                var deathIdx = -1;
                if (partList.length - 1 == beforeList.length) {
                    //입장 상황이다
                    console.log('-----new one enter situ!!-----');
                    for (var i = 0; i < partList.length; i++) {
                        if (!beforeList.includes(partList[i].partEmail)) {
                            newOneIdx = i; //partList에서 newOndIdx는 새로운 참가자
                            break;
                        }
                    }

                    //     for(var i =0;i<balls.length;i++)
                    //     ballAttr(50, Math.random() * h,Math.random() * w, 0, 0);

                    //     for (var i = 0; i < partList.length; i++) {
                    //         if (!beforeList.includes(partList[i].partEmail)) {
                    //             console.log('새로운거잖아???');
                    //             console.log('partList에서 인덱스' + i);
                    //             console.log(partList[i]);
                    //             createBall(Math.random() * h, Math.random() * w, i);
                    //         }
                    //     }
                } else if (partList.length == beforeList.length - 1) {
                    //퇴장 상황
                    console.log('-----Erazze situ!!!-----');
                    var tmp = [];
                    for (var i = 0; i < partList.length; i++) {
                        tmp.push(partList[i].partEmail);
                    }
                    for (var i = 0; i < beforeList.length; i++) {
                        if (!tmp.includes(beforeList[i])) {
                            deathIdx = i;
                            // beforeList에서 deathIdx는 죽은인원의 인덱스;
                            break;
                        }
                    }
                    console.log('-----삭제전 ball, ballCo-----');
                    console.log(balls);
                    console.log(ballCoords);
                    balls.splice(deathIdx, 1);
                    ballCoords.splice(deathIdx, 1);
                    console.log('-----삭제후 ball, ballco-----');
                    console.log(balls);
                    console.log(ballCoords);
                    console.log('-----삭제전 con-----');
                    var er = d.getElementById(`ssazip${beforeList[deathIdx]}`);
                    console.log(er.parentNode);
                    er.parentNode.removeChild(er);
                    console.log('-----삭제 후 con-----');
                    console.log(d.getElementById('oldPlace'));
                }
                if (newOneIdx != -1) {
                    console.log('new ball cre');
                    createBall(Math.random() * h, Math.random() * w, -1, newOneIdx);
                }
                for (var k = 0; k < num; k++) {
                    console.log('-----default ballmaker 초기나 이전 공-----');
                    console.log(k + '번째');
                    //만들 공 갯수
                    // if(document.getElementById(`ssazip${this.participants[i].partEmail}`)) continue;
                    if (newOneIdx != -1 && k == newOneIdx) {
                        console.log(k + '는 새로운친구야 패스');
                        continue;
                    }
                    // if (deathIdx != -1 && i == deathIdx) continue;
                    createBall(Math.random() * h, Math.random() * w, k, -1);
                }
                // if (deathIdx != -1) {
                //     console.log('era ball');
                //     createBall(Math.random() * h, Math.random() * w, i, newOneIdx, deathIdx);
                // }

                // for (var i = 0; i < num; i++) {
                //     //만들 공 갯수
                //     // if(document.getElementById(`ssazip${this.participants[i].partEmail}`)) continue;
                //     console.log('-----ballmaker-----');
                //     console.log(i);

                //     // if (con.childNodes.length != 2 && con.childNodes.length - 2 > partList.length) {
                //     //     //참가자보다 싸집이들이 많으면 삭제가 필요하다
                //     //     console.log('----Eraze Ball-----');
                //     //     console.log('con.childNodes');
                //     //     console.log(con.childNodes);
                //     //     for (var j = 0; j < con.childNodes.length; j++) {
                //     //         //기존 싸집이를 돌리면서
                //     //         console.log(j + '번째 싸집이');
                //     //         console.log('con.childNodes[j]');
                //     //         console.log(con.childNodes[j]);
                //     //         if (con.childNodes[j].id == 'svg' || con.childNodes[j].id == 'gravitybtn') {
                //     //             console.log('con.childNodes[j]패스');
                //     //             console.log(con.childNodes[j]);
                //     //             continue;
                //     //         }
                //     //         console.log('partList');
                //     //         console.log(partList);
                //     //         var erazeCompletedflag = false;
                //     //         for (var k = 0; k < partList.length; k++) {
                //     //             //새 참여자 리스트에 있나 없나
                //     //             // console.log('k');
                //     //             console.log(k + '번째 참여자');
                //     //             console.log('partList[k].partEmail');
                //     //             console.log(partList[k].partEmail);
                //     //             var email = 'ssazip' + partList[k].partEmail;
                //     //             if (email == con.childNodes[j].id) {
                //     //                 //있음 다행
                //     //                 // balls.pop(j);
                //     //                 // console.log('balls');
                //     //                 // console.log(balls);
                //     //                 // console.log('balls[j].partEmail');
                //     //                 // console.log(balls[j].partEmail);
                //     //                 //일치하는게 있다 탐색 중지
                //     //                 console.log('일치하는게 있다 탐색 중지');
                //     //                 break;
                //     //             }
                //     //             if (k == partList.length - 1) {
                //     //                 //다 검사해봤느데 없다?
                //     //                 console.log('없는데?');
                //     //                 console.log(con.childNodes[j]);
                //     //                 console.log(con.children[j]);
                //     //                 console.log(con.childNodes[j].id);
                //     //                 console.log(con.children.namedItem(`${con.childNodes[j].id}`));
                //     //                 con.removeChild(con.children.namedItem(`${con.childNodes[j].id}`));
                //     //                 erazeCompletedflag = true;
                //     //             }
                //     //         }
                //     //         if (erazeCompletedflag) break;
                //     //     }
                //     //     // var cell = document.getElementById('asd');
                //     //     // console.log('싸집이 지우고 ㄴ123121ㅓㅏㅟㅏㅜ');
                //     //     // while (cell.hasChildNodes()) {
                //     //     //     cell.removeChild(cell.firstChild);
                //     //     // }
                //     //     break;
                //     // }

                //     // con.removeChild(ball);

                //     createBall(Math.random() * h, Math.random() * w, i);
                // }

                // console.log(participants)
                // console.log(this.participants)
                // console.log(participantsVuex)
                // console.log(this.participantsVuex);

                // console.log(parts)
                // createBall(Math.random() * h, Math.random() * w, "123");
                // }
                console.log('-----------------------run------------------');
                console.log(JSON.parse(JSON.stringify(balls)));
                console.log(JSON.parse(JSON.stringify(ballCoords)));
                firstrun();
                d.getElementById('but2').addEventListener(
                    'click',
                    function() {
                        gravOn();
                    },
                    false
                );
                d.getElementById('but2').addEventListener(
                    'mouseover',
                    function() {
                        exempt = true;
                    },
                    false
                );
                d.getElementById('but2').addEventListener(
                    'mouseout',
                    function() {
                        exempt = false;
                    },
                    false
                );

                d.getElementById('but1').addEventListener(
                    'click',
                    function() {
                        restart();
                        gravOff();
                    },
                    false
                );
                d.getElementById('but1').addEventListener(
                    'mouseover',
                    function() {
                        exempt = true;
                    },
                    false
                );
                d.getElementById('but1').addEventListener(
                    'mouseout',
                    function() {
                        exempt = false;
                    },
                    false
                );

                // var cell = document.getElementById('asd');
                //     console.log(cell);
                // while (cell.hasChildNodes()) {
                //     console.log('!!!!!@!#!@#!@#@!!#####');
                //     cell.removeChild(cell.firstChild);
                // }
            }

            start();

            this.beforeParts = [];
            for (var i = 0; i < partList.length; i++) {
                this.beforeParts.push(partList[i].partEmail);
            }
            this.beforeBalls = balls.slice();
            this.beforeBallCoords = ballCoords.slice();

            //window.addEventListener('load', start, false);
            window.addEventListener('resize', win, false);
            window.addEventListener('scroll', iniscrl, false);
        },
    },
};
</script>

<style>
.banana {
    background-image: url(/img/ssazip.43ffb363.png);
}
/* Nothing here needed for app */

/* html{
    height:100%;
}
body{
    background-color:#99ccff;
}
sup{
    font-size: 45%;
} */
/* span{
    display:inline-block;
    position:relative;
    padding:4px;
    width:350px;
    line-height: 18px;
    user-select: none;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
    cursor: default;
    transform:rotate(-5deg);
    background-color:rgba(0,0,0,0.5);
    border-radius:5px;
    font: 30px verdana;
    color: #fff; 
    text-align: center;
} */
/* p {
    margin: 5px;
} */

/* ul {
    padding-left:20px; 

}

li {
    text-align:left;
    padding-top:5px;
} */
</style>
