package com.chkip.telecavegame;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
	
	@PostMapping(value = "/start", produces ="application/xml")
	public String start() {
		return """
		<Response>
			<Say language="fr-FR">
				Bienvenue dans le jeu Tele-Caverne sur serveur vocal interactif. Pour démarrer votre quête, appuyez sur étoile. Si vous ne souhaitez pas jouer, vous pouvez raccrocher.
			</Say>
			<Gather numDigits="1" action="/enter" method="POST"/>
		</Response>
			""";
	}
	
	@PostMapping(value = "/enter", produces = "application/xml")
	public String enter() {
		return """
				<Response>
					<Say language="fr-FR">
						Vous avez décidé de jouer à Tele-Caverne. Bravo ! Vous voici à présent devant une falaise abrupte. Pour chercher l'entrée de la caverne, tapez 1. Pour contourner la falaise, tapez 2.
					</Say>
					<Gather numDigits="1" action="/falaise" method="POST"/>
				</Response>
				""";
	}
	
	@PostMapping(value = "/falaise", produces = "application/xml")
	public String falaise(@RequestParam("Digits") String digits) {
		return switch (digits) {
		case "1" -> """
				<Response>
					<Say language="fr-FR">
					 	Le mot de passe pour entrer est "Mellon". Pour dire "melon" et entrer, tapez 1. Pour rebrousser chemin, tapez 2.
					</Say>
					<Gather numDigits="1" action="/monstre" method="POST"/>
				</Response>
				""";
		case "2" -> """
				<Response>
					<Say language="fr-FR">
						En voulant contourner la falaise, vous faites tomber un galet dans le point d'eau au bord du chemin. Un tentacule sort de l'eau et vous attrape la cheville. Pour vous enfuir, tapez 1. Pour affronter le monstre tentaculaire, tapez 2.
					</Say>
					<Redirect method="POST">/enter</Redirect>
				</Response>
				""";
		default -> """
				<Response>
					<Redirect method="POST">/enter</Redirect>
				</Response>
				""";
		};
	}
	
	

}
