<div class="team-container">

	<div class="team-content">

		<div class="player-card" id="${handle.getData()}">

			<img
				alt="${image.getAttribute("alt")}"
				class="profile hidden"
				data-fileentryid="${image.getAttribute("fileEntryId")}"
				src="${image.getData()}"
			/>
			<img
				alt="${imageFull.getAttribute("alt")}"
				class="profile-full"
				data-fileentryid="${imageFull.getAttribute("fileEntryId")}"
				src="${imageFull.getData()}"
			/>

		</div>

		<div class="player-content-top">

			<p class="player-handle">${handle.getData()}</p>

			<h1 class="player-number">#${number.getData()}</h1>

		</div>

		<div class="player-content-bottom ${position.getData()}">

			<p class="player-position">Position: ${position.getData()}</p>

			<p class="player-name">Name: ${name.getData()}</p>

		</div>

	</div>

</div>

<script>
	document.getElementById("layout-column_column-2").addEventListener('click',
		function(event) {
			var target = event.target
			var parent = target.parentElement;
			if (target.className === 'profile-full') {

				if (parent.nodeName === 'PICTURE') {
					parent = parent.parentElement;
				}

				target.classList.toggle('hidden');
				parent.firstElementChild.classList.toggle('hidden');
			}

			if (target.className === 'profile') {
				target.classList.toggle('hidden');
				parent.lastElementChild.classList.toggle('hidden');

				if (parent.lastElementChild.nodeName === 'PICTURE') {
					return parent.lastElementChild.childNodes[1].classList.toggle('hidden')
				}

			}
		}
	)
</script>