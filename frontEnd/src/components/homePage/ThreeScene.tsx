import { useEffect, useRef } from "react";
import * as THREE from "three";
import gsap from "gsap";

import { GLTFLoader } from "three/examples/jsm/loaders/GLTFLoader.js";
const ThreeScene = () => {
  const canvasRef = useRef() as any;

  useEffect(() => {
    // Renderer
    const renderer = new THREE.WebGLRenderer({
      canvas: canvasRef.current,
      antialias: true,
    });
    renderer.setSize(450, 740);
    renderer.setPixelRatio(1);
    renderer.shadowMap.enabled = true;
    renderer.shadowMap.type = THREE.PCFSoftShadowMap;

    // Scene
    const scene = new THREE.Scene();
    scene.background = new THREE.Color("black");

    // Camera
    const camera = new THREE.PerspectiveCamera(75, 450 / 740, 0.1, 1000);
    camera.position.set(-5, 0, 12);
    scene.add(camera);

    // Light
    const ambientLight = new THREE.AmbientLight("white", 2);
    scene.add(ambientLight);

    const spotLight = new THREE.SpotLight("white", 0.7);
    spotLight.position.set(0, 150, 100);
    spotLight.castShadow = true;
    spotLight.shadow.mapSize.width = 1024;
    spotLight.shadow.mapSize.height = 1024;
    spotLight.shadow.camera.near = 1;
    spotLight.shadow.camera.far = 200;
    scene.add(spotLight);

    const loader = new GLTFLoader();
    loader.load(
      "/models/cinema5.glb", // 모델 파일 경로를 입력하세요.
      (gltf) => {
        console.log(gltf);
        const model = gltf.scene;
        model.position.set(-5 + 0.21, -0.35, 11.4);
        model.scale.set(0.05, 0.08, 0.05);
        scene.add(model);
        model.rotation.y += 3.15;
        gltf.scene.rotation.x += 0.05;
      }
    );

    loader.load(
      "/models/star2.glb", // 모델 파일 경로를 입력하세요.
      (gltf) => {
        console.log(gltf);
        const model = gltf.scene;
        model.position.set(houses[1].x, 6, houses[1].z);
        model.scale.set(0.1, 0.1, 0.1);
        scene.add(model);
        // function animate() {
        //   requestAnimationFrame(animate); //1초에 60번 실행됨.

        //   //회전
        //   gltf.scene.rotation.y += 0.005;
        //   renderer.render(scene, camera);
        // }
        // animate();
      }
    );
    loader.load(
      "/models/lala.glb", // 모델 파일 경로를 입력하세요.
      (gltf) => {
        console.log(gltf);
        const model = gltf.scene;
        model.position.set(houses[1].x, houses[1].y - 0.5, houses[1].z);
        model.scale.set(
          window.innerHeight * 0.0007,
          window.innerHeight * 0.0007,
          window.innerHeight * 0.0007
        );
        scene.add(model);
      }
    );

    loader.load(
      "/models/moon.glb", // 모델 파일 경로를 입력하세요.
      (gltf) => {
        console.log(gltf);
        const model = gltf.scene;
        model.position.set(houses[1].x, houses[1].y + 6.5, houses[1].z);
        model.scale.set(2, 2, 2);
        scene.add(model);
      }
    );

    loader.load(
      "/models/cosmonaut.glb", // 모델 파일 경로를 입력하세요.
      (gltf) => {
        console.log(gltf);
        const model = gltf.scene;
        model.position.set(houses[2].x - 1, houses[2].y + 2.5, houses[2].z);
        model.scale.set(0.004, 0.004, 0.004);
        scene.add(model);
        function animate() {
          requestAnimationFrame(animate);

          // 모델의 y축 위치 변경
          model.position.y += Math.sin(Date.now() * 0.001) * 0.01; // 위아래로 움직임

          renderer.render(scene, camera);
        }
        animate();
      }
    );

    loader.load(
      "/models/planet.glb", // 모델 파일 경로를 입력하세요.
      (gltf) => {
        console.log(gltf);
        const model = gltf.scene;
        model.position.set(houses[2].x + 1, houses[2].y, houses[2].z - 3);
        model.scale.set(0.2, 0.2, 0.2);
        scene.add(model);
        function animate() {
          requestAnimationFrame(animate); //1초에 60번 실행됨.

          //회전
          gltf.scene.rotation.y += 0.05;
          renderer.render(scene, camera);
        }
        animate();
      }
    );

    loader.load(
      "/models/saturn.glb", // 모델 파일 경로를 입력하세요.
      (gltf) => {
        console.log(gltf);
        const model = gltf.scene;
        model.position.set(houses[2].x + 3, houses[2].y + 8, houses[2].z - 7);
        model.scale.set(1.5, 1.5, 1.5);
        scene.add(model);
        model.rotation.x += 0.3;
        model.rotation.z += 0.2;
        function animate() {
          requestAnimationFrame(animate); //1초에 60번 실행됨.

          //회전
          gltf.scene.rotation.y += 0.03;
          renderer.render(scene, camera);
        }
        animate();
      }
    );

    const houses = [
      { x: -5, y: -2, z: 7 },
      { x: -5, y: 0, z: -100 },
      { x: -5, y: 8.5, z: -100 },
      { x: -5, y: -2, z: 7 },
    ];

    let currentSection = 0;
    function setSection() {
      const newSection = Math.round(window.scrollY / window.innerHeight);

      if (currentSection !== newSection) {
        console.log("animation!!");
        gsap.to(camera.position, {
          duration: 1,
          x: houses[newSection].x,
          y: houses[newSection].y + 2,
          z: houses[newSection].z + 5,
        });
        currentSection = newSection;
      }
    }

    const draw = () => {
      renderer.render(scene, camera);
      renderer.setAnimationLoop(draw);
    };

    const handleResize = () => {
      const aspect = 450 / 740;
      camera.aspect = aspect;
      camera.updateProjectionMatrix();

      renderer.setSize(450, 740);
    };

    window.addEventListener("scroll", setSection);
    window.addEventListener("resize", handleResize);
    window.addEventListener("resize", handleResize);

    draw();

    return () => {
      window.removeEventListener("scroll", setSection);
      window.removeEventListener("resize", handleResize);
      renderer.dispose();
    };
  }, []);

  return <canvas ref={canvasRef} className="fixed top-0" />;
};

export default ThreeScene;
