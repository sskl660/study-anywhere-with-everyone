(function () {
  /*
  Play Balls 2 (full page version).
  Straight JavaScript!
  kurt.grigg@yahoo.co.uk
  */
  var numberOfBalls = 15;
  var ballVelocity = 2;
  var ballHardness = 0.85;
  var wallHardness = 0.50;
  var floorHardness = 0.60;
  var minRadius = 35;
  var maxRadius = 35;
  var ballColour = '#dd0000'; //Leave blank for random colours.
  var milliSeconds = 30;
  var gameGravity = 1.0;
  var vectorLineColour = '#ff0000';

  var then = performance.now();
  var h, w, balls = [], ballCoords = [];
  var cony = 0;
  var conx = 0;
  var d = document;
  var force = 0.05;
  var exempt = false;
  var newBall = false;
  var drop = false;
  var m = {down: false,x1: 0,y1: 0,x2: 0,y2: 0,}

  document.oncontextmenu = function (){
      return false;
  }

  window.addEventListener('mousedown', function(e) {
      if (!e) {e = window.event;}
      m.down = true;
      m.x1 = m.x2 = e.pageX - scrl(1);
      m.y1 = m.y2 = e.pageY - scrl(0);

      d.body.style.cursor = 'crosshair';
      d.getElementById('svg').style.visibility = 'visible';
      d.getElementById('vec').setAttribute('x1', m.x1);
      d.getElementById('vec').setAttribute('y1', m.y1);
  }, false);

  window.addEventListener("mousemove", function(e) {
      if (!e) {e = window.event;}
      if (m.down) {
          m.x2 = e.pageX - scrl(1);
          m.y2 = e.pageY - scrl(0);
      }
      else {
          m.x1 = m.x2 = e.pageX - scrl(1);
          m.y1 = m.y2 = e.pageY - scrl(0);
      }
      d.getElementById('vec').setAttribute('x2', m.x2);
      d.getElementById('vec').setAttribute('y2', m.y2);
  }, false);

  window.addEventListener("mouseup", function(e) {
      if (!e) {e = window.event;}
      if (m.down) {
          m.down = false;
          d.body.style.cursor = 'default';
          d.getElementById('svg').style.visibility = 'hidden';

          var dx = (m.x1 - m.x2);
          var dy = (m.y1 - m.y2);
          var dist =  Math.sqrt(dx * dx + dy * dy);

          var f = (drop)?1:0.3;

          var nvx = (dx / dist * -(dist * force)) * f;
          var nvy = (dy / dist * -(dist * force)) * f;

          // ??????????????? ??? ??????
          // if (!exempt && dist > 0) {
          //     createBall(m.y1,m.x1,nvy,nvx);
          // }
      }
  }, false);

  function scrl(a) {
      var y, x;
      y = window.pageYOffset;
      x = window.pageXOffset;
      return (a == 0) ? y : x;
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
          var gen = 50 + Math.random() * 205|0;
          rgb.push(gen);
      }
      return (typeof ballColour == 'undefined' || ballColour == '')?'rgb('+rgb.toString()+')':ballColour;
  }

  function win() {
      var ddw = d.documentElement.clientWidth;
      var ddh = d.documentElement.clientHeight;
      var scrollBarRight = (typeof ddw == 'number') ? window.innerWidth - ddw : 0;
      var scrollBarBottom = (typeof ddh == 'number') ? window.innerHeight - ddh : 0;
      h = window.innerHeight - scrollBarBottom -1;
      w = window.innerWidth - scrollBarRight -1;
  }

  var con = d.createElement('div');
  con.setAttribute('style', 'display:block;'
      +'position:absolute;'
      +'top:0px;left:0px;'
      +'height:100%;width:100%;'
      +'background:transparent;'
      +'overflow:hidden;' 
      +'visibility:hidden;');

  con.innerHTML += '<svg id="svg" xmlns="http://www.w3.org/2000/svg" width="100%" height="100%"'
  +' style="display:block;position:absolute;top:0px;left:0px;visibility:hidden;z-index:1000;">'
  +'<line id="vec" x1="0" y1="0" x2="0" y2="0" stroke="'+vectorLineColour+'" stroke-width="1.5" stroke-dasharray="3,3">'
  +'</svg>';

  d.body.appendChild(con);

  function xy (a, s) {
      return (a * s / 100);
  }


  function createBall(y,x,nvy,nvx) {
      var r = minRadius + Math.random() * maxRadius|0;
      var ball = d.createElement('div');
      ball.setAttribute('style', 'display:block;'
          +'position:absolute;'
          +'height:'+r+'px;'
          +'width:'+r+'px;'
          +'top:0px;left:0px;'
          +'background-color:'+randomColour()+';'
          +'border-radius:50%;'
          +'box-shadow:inset 0 0 '+xy(1,r)+'px '+xy(1,r)+'px rgba(0,0,0,0.2),'
          +'inset 0 -'+xy(25,r)+'px '+xy(50,r)+'px rgba(0,0,0,0.4);'
          +'opacity:1.0;');

      var glass = d.createElement('div');
      glass.setAttribute('style', 'display: block;'
          +'position:absolute;'
          +'height:'+xy(100)+'px;'
          +'width:'+xy(100)+'px;'
          +'margin:auto;top:0;bottom:0;left:0;right:0;'
      +'border-radius:50%;');

      var shine = d.createElement('div');
      shine.setAttribute('style', 'display:block;'
    +'position:absolute;'
          +'margin:auto;'
          +'top:2%;'
          +'left:0;right:0;'
    +'border-radius:50%;'
    +'width:72%;'
    +'height:54%;'
    +'background-image:linear-gradient(to bottom, rgba(255,255,255,0.9) 2%, '
          +'rgba(255,255,255,0.7) 30%, transparent 100%);');
      glass.appendChild(shine);
      ball.appendChild(glass);

      setTimeout(function() {
          ball.style.visibility = 'visible';
      },50);

      con.appendChild(ball);
      balls.push(ball);
      ballAttr(r,y,x,nvy,nvx);
  }

  var gravCon = d.createElement('div');
  gravCon.setAttribute('style', 'display: block;'
      +'position: fixed;'
      +'margin: 4px;'
      +'top:0;right:0;'
      +'border-radius:5px;'
      +'height: 30px;'
      +'width: 130px;'
+'padding: 5px;'
      +'background-color:#ee0000;'
+'text-align: center;'
+'font: italic bold 16px verdana;'
+'color: #fff;'
+'line-height: 30px;'
      +'letter-spacing: 0.5px;'
      +'cursor:default;'
      +'z-index: 1000;' 
      +'-moz-user-select: none;user-select: none;-webkit-user-select: none;-ms-user-select: none;'
      +'visibility:visible;');

  gravCon.innerHTML = 'Gravity <input type="radio" style="cursor:pointer;" name="c" id="but1" checked>'
                             +'<input type="radio" style="cursor:pointer;"name="c" id="but2">';

  con.appendChild(gravCon);

  function ballAttr(rad,y,x,nvy,nvx) {
      if (balls.length > numberOfBalls ) {
          newBall = true;
      }

      var zx = (newBall)?x-nvx:0;
      var zy = (newBall)?y-nvy:0;

      var vex = (newBall)?nvx:-ballVelocity + Math.random() * (ballVelocity * 2);
      var vey = (newBall)?nvy:-ballVelocity + Math.random() * (ballVelocity * 2);

      var b = {
      r: (rad * 0.5),
      m: rad * 0.5,
      x: x, 
      y: y, 
      px: zx, 
      py: zy, 
      ax: 0, 
      ay: 0, 
      vx: vex, 
      vy: vey};

     ballCoords.push(b);
  }


  function animate() {
      if (drop) {
          return false;
      }
      else {
          for (var i = 0; i < balls.length; i++) {
              var b = ballCoords[i];
              b.x += b.vx;
              b.y += b.vy;
              b.px = b.x - (50 * b.vx / 100);
              b.py = b.y - (50 * b.vy / 100);

         }
      }
  }

  function zeroGravityBorderCollisions() {
      for (var i = 0; i < balls.length; i++) {
          b = ballCoords[i];
          if (b.x > w - b.r) {
              b.x = w - b.r;
              b.vx *= -1;
          }
          else if (b.x < b.r) {
              b.x = b.r;
              b.vx *= -1;
          }

          if (b.y > h - b.r) {
              b.y = h - b.r;
              b.vy *= -1;
          }
          else if (b.y < b.r) {
              b.y = b.r;
              b.vy *= -1;
          }
      }
  }

  function gravityBorderCollisions(){
      for (var i = 0; i < balls.length; i++) {
          var b = ballCoords[i];

          var x = b.x;
          var y = b.y;

          if (x - b.r < 0){
              var vx = (b.px - b.x) * wallHardness;
              b.x = b.r;
              b.px = b.x - vx;
          }
          else if (x + b.r > w){
              var vx = (b.px - b.x) * wallHardness;
              b.x = w - b.r;
              b.px = b.x - vx;
          }
          if (y - b.r < 0){
              var vy = (b.py - b.y) * wallHardness;
              b.y = b.r;
              b.py = b.y - vy;
          }
          else if (y + b.r > h){
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

                  b1.tx = (b1.x - b1.px);
                  b1.ty = (b1.y - b1.py);
                  b2.tx = (b2.x - b2.px);
                  b2.ty = (b2.y - b2.py);

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

                          var rvx1 = v1 * Math.cos(a1-ca);
                          var rvy1 = v1 * Math.sin(a1-ca);
                          var rvx2 = v2 * Math.cos(a2-ca);
                          var rvy2 = v2 * Math.sin(a2-ca);

                          var evx1 = ((b1.m - b2.m * ballHardness) * rvx1 + (b2.m + b2.m * ballHardness) * rvx2) / (b1.m + b2.m);
                          var evx2 = ((b1.m + b1.m * ballHardness) * rvx1 + (b2.m - b1.m * ballHardness) * rvx2) / (b1.m + b2.m);
                          var evy1 = rvy1;
                          var evy2 = rvy2;

                          b1.tx =  Math.cos(ca) * evx1 + Math.cos(ca + Math.PI/2) * evy1;
                          b1.ty =  Math.sin(ca) * evx1 + Math.sin(ca + Math.PI/2) * evy1;
                          b2.tx =  Math.cos(ca) * evx2 + Math.cos(ca + Math.PI/2) * evy2;
                          b2.ty =  Math.sin(ca) * evx2 + Math.sin(ca + Math.PI/2) * evy2;
                      }
                      else {
                          var v1 = Math.sqrt(b1.vx * b1.vx + b1.vy * b1.vy);
                          var v2 = Math.sqrt(b2.vx * b2.vx + b2.vy * b2.vy);

                          var a1 = Math.atan2(b1.vy, b1.vx);
                          var a2 = Math.atan2(b2.vy, b2.vx);

                          var rvx1 = v1 * Math.cos(a1-ca);
                          var rvy1 = v1 * Math.sin(a1-ca);
                          var rvx2 = v2 * Math.cos(a2-ca);
                          var rvy2 = v2 * Math.sin(a2-ca);

                          var evx1 = ((b1.m - b2.m) * rvx1 + (b2.m + b2.m) * rvx2) / (b1.m + b2.m);
                          var evx2 = ((b1.m + b1.m) * rvx1 + (b2.m - b1.m) * rvx2) / (b1.m + b2.m);
                          var evy1 = rvy1;
                          var evy2 = rvy2;

                          b1.vx =  Math.cos(ca) * evx1 + Math.cos(ca + Math.PI/2) * evy1;
                          b1.vy =  Math.sin(ca) * evx1 + Math.sin(ca + Math.PI/2) * evy1;
                          b2.vx =  Math.cos(ca) * evx2 + Math.cos(ca + Math.PI/2) * evy2;
                          b2.vy =  Math.sin(ca) * evx2 + Math.sin(ca + Math.PI/2) * evy2;
                      }
                  b1.px = (b1.x - b1.tx);
                  b1.py = (b1.y - b1.ty);
                  b2.px = (b2.x - b2.tx);
                  b2.py = (b2.y - b2.ty);
                  }
              }
          }
      }
  }

  function gravity() {
      for (var i = 0; i < balls.length; i++) {
          var b = ballCoords[i];
          b.ay += gameGravity;
      }
  }

  function inertia(delta) {
      for (var i = 0; i < balls.length; i++) {
          var b = ballCoords[i];

          var x = (b.x*2 - b.px);
          var y = (b.y*2 - b.py);

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

  function assignToHTML() {
      for (var i = 0; i < balls.length; i++) {
          var b = ballCoords[i];
          balls[i].style.transform = 'translate3d('+(b.x - b.r)+'px, '+(b.y - b.r)+'px, 0) rotate(22.5deg)';
      }
  }

  function restart() {
      for (var i = 0; i < balls.length; i++) {
          var b = ballCoords[i];
          b.vx = (b.x - b.px);
          b.vy = (b.y - b.py);
      }
  }

  function step() {
      var steps = 2;
      var delta = 1/steps;
      for (var i = 0; i < steps; i++){
          if (drop) {
             //Thanks to Florian Boesch for this anti-jitter doubling trick.
             gravity();
             accelerate(delta);
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
      if ((now - then) > milliSeconds) {
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

  function start() {
      win();
      for (var i = 0; i < numberOfBalls; i++) {
          createBall(Math.random() * h,Math.random() * w);
      }
      run();
      d.getElementById('but2').addEventListener("click", function(){gravOn();},false);
      d.getElementById('but2').addEventListener("mouseover", function(){exempt = true;},false);
      d.getElementById('but2').addEventListener("mouseout", function(){exempt = false;},false);

      d.getElementById('but1').addEventListener("click", function(){restart();gravOff();},false);
      d.getElementById('but1').addEventListener("mouseover", function(){exempt = true;},false);
      d.getElementById('but1').addEventListener("mouseout", function(){exempt = false;},false);
  }

  window.addEventListener("load", start, false);
  window.addEventListener("resize", win, false);
  window.addEventListener("scroll", iniscrl, false);

})();